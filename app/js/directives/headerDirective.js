stp.directive('stpHeader', function($document){

	return {
				restrict: 'E',
				replace: true,
				templateUrl: 'app/partials/header.tpl.html',
				controller: 'headerController',
				link: function( scope, element){
			   	}
			};
});