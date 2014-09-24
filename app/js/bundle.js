var bundle = {};

bundle.config = 'app/js/config.js';

//Controllers
bundle.globalController = 'app/js/controllers/globalController.js';
bundle.homeController = 'app/js/controllers/homeController.js';
bundle.headerController = 'app/js/controllers/headerController.js';
bundle.signupController = 'app/js/controllers/signupController.js';
bundle.signinController = 'app/js/controllers/signinController.js';

//Services
bundle.dataService = 'app/js/services/dataService.js';

//Directives
bundle.header = 'app/js/directives/headerDirective.js';

for(var i in bundle){
	var obj = bundle[i];
   	document.write('<script type="text/javascript" src="' + obj + '"></script>');
}

