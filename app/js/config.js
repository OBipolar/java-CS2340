var stp = angular.module('stp',['ngRoute', 'ui.bootstrap']).
	config(function($routeProvider){
		$routeProvider.
			when('/signup', {
								templateUrl: 'app/partials/signup.tpl.html', 
								controller: signupController
							}).
			otherwise({
						redirectTo: '/home', 
						templateUrl:'app/partials/home.tpl.html', 
						controller: homeController
					  });
});
