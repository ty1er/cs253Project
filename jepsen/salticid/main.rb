require 'fileutils'

load __DIR__/'etcd.rb'
load __DIR__/'mongo.rb'
load __DIR__/'nuodb.rb'
load __DIR__/'postgres.rb'
load __DIR__/'redis.rb'
load __DIR__/'riak.rb'
load __DIR__/'hazelcast.rb'

role :base do
  task :setup do
    sudo do
      exec! 'yum groupinstall "Development Tools"'
      exec! 'yum install -qy ntp curl wget git-core vim psmisc iptables bind-utils telnet nmap', :echo => true
    end
  end

  task :shutdown do
    sudo { shutdown '-h', :now }
  end

  task :reboot do
    sudo { reboot }
  end
end

role :jepsen do
  task :setup do
    base.setup
    #etcd.setup
    #mongo.setup
    #postgres.setup
    #redis.setup
    #riak.setup
  end
 
  task :slow do
    sudo { exec! 'tc qdisc add dev eth0 root netem delay 50ms 5ms distribution normal' }
  end

  task :flaky do
    sudo { exec! 'tc qdisc add dev eth0 root netem loss 20% 75%' }
  end

  task :fast do
    sudo { tc :qdisc, :del, :dev, :eth0, :root }
  end

  task :partition do
    sudo do
      n3 = (getent 'ahosts', 'dblab-rack12').split("\n").reject {|line| line.start_with?("127.")}.first.split(" ").first
      n4 = (getent 'ahosts', 'dblab-rack13').split("\n").reject {|line| line.start_with?("127.")}.first.split(" ").first
      n5 = (getent 'ahosts', 'dblab-rack14').split("\n").reject {|line| line.start_with?("127.")}.first.split(" ").first
      if ['n1', 'n2'].include? name
        log "Partitioning from n3, n4 and n5."
        iptables '-A', 'INPUT', '-s', n3, '-j', 'DROP'
        iptables '-A', 'INPUT', '-s', n4, '-j', 'DROP'
        iptables '-A', 'INPUT', '-s', n5, '-j', 'DROP'
      end
      iptables '--list', :echo => true
    end
  end

  task :partition_reject do
    sudo do
      n1 = (getent 'ahosts', 'dblab-rack10').split("\n").reject {|line| line.start_with?("127.")}.first.split(" ").first
      n2 = (getent 'ahosts', 'dblab-rack11').split("\n").reject {|line| line.start_with?("127.")}.first.split(" ").first
      n3 = (getent 'ahosts', 'dblab-rack12').split("\n").reject {|line| line.start_with?("127.")}.first.split(" ").first
      n4 = (getent 'ahosts', 'dblab-rack13').split("\n").reject {|line| line.start_with?("127.")}.first.split(" ").first
      n5 = (getent 'ahosts', 'dblab-rack14').split("\n").reject {|line| line.start_with?("127.")}.first.split(" ").first
      if ['n1', 'n2'].include? name
        log "Partitioning from n3, n4 and n5."
        iptables '-A', 'INPUT', '-s', n3, '-j', 'REJECT'
        iptables '-A', 'INPUT', '-s', n4, '-j', 'REJECT'
        iptables '-A', 'INPUT', '-s', n5, '-j', 'REJECT'
      else
        log "Partitioning from n1, n2"
        iptables '-A', 'INPUT', '-s', n1, '-j', 'REJECT'
        iptables '-A', 'INPUT', '-s', n2, '-j', 'REJECT'
      end

      iptables '--list', :echo => true
    end
  end

  task :drop_pg do
    sudo do
      log "Dropping all PG traffic."
      iptables '-A', 'INPUT', '-p', 'tcp', '--dport', 5432, '-j', 'DROP'
      iptables '--list', :echo => true
    end
  end

  task :heal do
    sudo do
      iptables '-F', :echo => true
      iptables '-X', :echo => true
      iptables '--list', :echo => true
    end
  end

  task :status do
    sudo do
      iptables '--list', :echo => true
    end
  end
end

group :jepsen do
  host "dblab-rack10"
  host "dblab-rack11"
  host "dblab-rack12"
  host "dblab-rack13"
  host "dblab-rack14"
  
  each_host do
    user :iabsa001
    role :base
#    role :cassandra
#    role :etcd
#    role :kafka
#    role :mongo
#    role :nuodb
#    role :postgres
#    role :redis
#    role :riak
#    role :zk
    role :hazelcast
    role :jepsen
    @password = ''
  end
end
