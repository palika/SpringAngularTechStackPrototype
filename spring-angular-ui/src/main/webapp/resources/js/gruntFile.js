module.exports = function(grunt) {
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-clean');
//	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-maven');
	grunt.loadNpmTasks('grunt-karma');
	
	var karmaConfig = function(configFile, customOptions) {
	    var options = { configFile: configFile, keepalive: true };
	    var travisOptions = process.env.TRAVIS && { browsers: ['Firefox'], reporters: 'dots' };
	    return grunt.util._.extend(options, customOptions, travisOptions);
	  };
	
	grunt.initConfig({
		distdir: 'dist',
		concatdir: 'concat',
		pkg : grunt.file.readJSON('package.json'),
		clean: ['<%= concatdir %>/*'],
		karma: {
		      unit: { configFile: 'test/config/unit.js' }
//		      watch: { options: karmaConfig('test/config/unit.js', { singleRun:false, autoWatch: true}) }
		    },
		concat: {
			options: {
		      // define a string to put between each file in the concatenated output
		      separator: ';'
		    },
		    dist: {
	          // the files to concatenate
	          src: ['src/**/*.js'],
	          // the location of the resulting JS file
	          dest: '<%= concatdir %>/angular-app.js'
	        },
	        angular:{
	        	src:['vendor/angular/angular.js', 'vendor/angular/angular-route.js'],
	            dest: '<%= concatdir %>/angular.js'
	        },
	        bootstrap: {
              src:['vendor/angular-ui/bootstrap/*.js'],
              dest: '<%= concatdir %>/bootstrap.js'
            },
            jquery: {
              src:['vendor/jquery/*.js'],
              dest: '<%= concatdir %>/jquery.js'
            }
		},
//		mavenPrepare: {//maven prepare is not needed, the create-resources goal will take care of this
//		    options: {
//		      resources: ['src/*','vendor/*']
//		    },
//		    prepare: {}
//		  },
		  mavenDist: {
		    options: {
		      warName: '/springmvc-angular-ui-0.0.1-SNAPSHOT',
		      deliverables: ['concat/**', '!non-deliverable.js']
		      ,gruntDistDir: '<%= distdir %>'
		    },
		    dist: {}
		  }
	});
	

	grunt.registerTask('default', [//'mavenPrepare',
//	                               'clean',
	                               'concat'
	                               ,'mavenDist'
	                               ,'karma:unit'
	                               ]);
};