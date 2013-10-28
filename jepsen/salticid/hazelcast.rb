role :hazelcast do
  task :setup do
      file = 'hazelcast-3.1.zip'

      # Go
      cd '/home/iabsa001'
      unless dir? 'hazelcast'
        unless file? file
          exec! "wget http://www.hazelcast.com/files/#{file}", :echo => true
        end
        exec! "unzip #{file} -d hazelcast"
      end
   end

  task :stop do
	exec! 'ps aux | grep hazelcast | grep -v grep | awk \'{ print $2 }\' | xargs kill -s kill'
  end

  task :start do
	cd '/home/iabsa001/hazelcast/bin'
	exec! 'server.sh', echo: true
  end
  
  task :restart do
    hazelcast.stop
    hazelcast.start
  end   
end