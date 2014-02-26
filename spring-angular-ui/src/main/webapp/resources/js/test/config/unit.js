/**
 * Configuration file for Karma tests
 */
module.exports = function(config){
    config.set({
		// base path, that will be used to resolve files and exclude
		basePath : '../..',
		frameworks:["jasmine"],
		// list of files / patterns to load in the browser, when the tests are running
		files : ['vendor/jquery/jquery.js',
				'vendor/angular/angular.js', 'vendor/angular/angular-route.js',
				// 'vendor/mongolab/mongolab-resource.js',
				'test/vendor/angular/angular-mocks.js',
				'vendor/angular-ui/**/*.js', 'src/**/*.js',
				'test/unit/**/*.spec.js', 'templates/**/*.html','templates/**/*.js'
				],
				
		/**
		 * Use ng-html2js preprocessor. Example of usage:https://github.com/karma-runner/karma-ng-html2js-preprocessor
		 * https://github.com/vojtajina/ng-directive-testing
		 */
		preprocessors : {
			'templates/**/*.html' : [ 'ng-html2js' ]
		},
		ngHtml2JsPreprocessor: {
	      // strip this from the file path
//	      stripPrefix: 'public/',
	      // prepend this to the
	      prependPrefix: 'resources/js/',
	      // or define a custom transform function
//	      cacheIdFromPath: function(filepath) {
//	        return cacheId;
//	      },
	      // setting this option will create only a single module that contains templates
	      // from all the files, so you can load them all with module('foo')
//	      moduleName: 'foo'
	    },

		// use dots reporter, as travis terminal does not support escaping
		// sequences
		// possible values: 'dots' || 'progress'
		reporters : 'progress',
		// these are default values, just to show available options

		// web server port
		port : 8089,
		// cli runner port
		runnerPort : 9109,
		urlRoot : '/__test/',
		// enable / disable colors in the output (reporters and logs)
		colors : true,
		// level of logging
		// possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO ||
		// LOG_DEBUG
		logLevel : LOG_DEBUG,
		// enable / disable watching file and executing tests whenever any file
		// changes
		autoWatch : false,
		// polling interval in ms (ignored on OS that support inotify)
		autoWatchInterval : 0,
		// Start these browsers, currently available:
		// - Chrome
		// - ChromeCanary
		// - Firefox
		// - Opera
		// - Safari
		// - PhantomJS
		browsers : [ 'Chrome' ],
		// Continuous Integration mode
		// if true, it capture browsers, run tests and exit
		singleRun : true
	})
};