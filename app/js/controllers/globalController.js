function globalController($scope, $location){
	$scope.route = function(route){
		$location.path(route);
	};
}