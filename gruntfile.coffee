module.exports = (grunt) ->
  grunt.loadNpmTasks 'grunt-bower-concat'
  grunt.initConfig
    bower_concat:
      all:
        dest: 'public/vendor.js'
        dependencies: {}
        # 'chai-jquery': ['jquery','chai']
        bowerOptions:
          relative: false

  grunt.registerTask "build", ["bower_concat"]
  grunt.registerTask "default", ["build"]
