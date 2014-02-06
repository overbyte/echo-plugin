//'use strict';

var echo = function (str, callback) {
	console.log('Conversation Checker is here');

	//window.echo = function(str, callback) {
		console.log('echoing: ' + str);
        cordova.exec(callback, function(err) {
            callback('Nothing to echo.');
        }, "Echo", "echo", [str]);
    //};
};


module.exports = echo;