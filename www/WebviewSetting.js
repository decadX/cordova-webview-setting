
var exec = require('cordova/exec');

module.exports = {
	set:function(callback) {
		exec(function(res){
			callback && callback(null,res);
		}, function(error){
			callback && callback(error);
		}, "WebviewSetting", "set", []);
	}
};
