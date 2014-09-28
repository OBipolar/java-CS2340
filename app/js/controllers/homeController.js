function homeController($scope, $location){
	$scope.route = function(route){
		$location.path(route);
	}
    
    $scope.submit = function(){
    	$scope.loginAttempted = true;
    	console.log($scope.data);
    	//login($scope.data);
	}
   
    function login(data){
	 	dataService.login($scope.data).then(function(data){
	 	});
	}

	$scope.signup = function(){
		$scope.route('signup');
	};
};
