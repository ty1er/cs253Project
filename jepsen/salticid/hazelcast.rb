role :hazelcast do
  task :setup do
      file = 'hazelcast-3.1.zip'

      # Go
      cd '/home/ubuntu'
      unless dir? 'hazelcast'
        unless file? file
          exec! "wget -nc http://www.hazelcast.com/files/#{file}", :echo => true
        end
        exec! "unzip #{file} -d hazelcast"
      end
      hazelcast.deploy
   end

  task :deploy do
    sudo do
      sudo_upload __DIR__/:hazelcast/'hazelcast.xml', '/home/ubuntu/hazelcast/bin/hazelcast.xml'
    end
    hazelcast.restart
  end

  task :stop do
	exec! 'ps aux | grep hazelcast | grep -v grep | awk \'{ print $2 }\' | xargs kill -s kill'
  end

  task :start do
	cd '/home/ubuntu/hazelcast/bin'
	exec! 'sh ./server.sh', :echo => true
  end
  
  task :restart do
    hazelcast.stop
    hazelcast.start
  end   
end
