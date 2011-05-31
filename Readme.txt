on first run:    

	mvn clean integration-test -Dcucumber.installGems=true

subsequent runs:

    	mvn clean integration-test
    	
to specify a profile
    	mvn clean integration-test -Dprofile=tag

to specify a tags set
    	mvn clean integration-test -Dprofile=tag -DcukeArgs="--tags @IE"
    	


Step definitions go in 'src/test/java'
Features go in 'features'

***********************************************************************************
*************************************************************************************

For Ruby tests:

	to Setup gems once ruby installed:
		mvn exec:exec -Djruby=setup -Djruby.path=<path>
	
		for example:
    		mvn exec:exec -Djruby=setup -Djruby.path=D:\DevEnv\jruby-1.6.2
    	
    to Run ruby tests located in features2:
    
    	mvn exec:exec -Djruby=rake -Djruby.path=<path>
	
		for example:
    		mvn exec:exec -Djruby=rake -Djruby.path=D:\DevEnv\jruby-1.6.2

Rakefile will run features located in features2, to keep them seperate from java ones









