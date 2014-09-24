var bundle = {};

bundle.config = 'app/js/config.js';

//Controllerss
bundle.global = 'app/js/controllers/globalController.js';
bundle.home = 'app/js/controllers/homeController.js';
bundle.signup = 'app/js/controllers/signupController.js';
bundle.signin = 'app/js/controllers/signinController.js';

//Services
bundle.dataService = 'app/js/services/dataService.js';

//Directives
bundle.header = 'app/js/directives/headerDirective.js';

for(var i in bundle){
	var obj = bundle[i];
   	document.write('<script type="text/javascript" src="' + obj + '"></script>');
}

