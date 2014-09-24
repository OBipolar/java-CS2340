stp.directive('stpFooter', function($document){
    return {
            restrict: 'E',
            replace: true,
            templateUrl: 'app/partials/footer.tpl.html',
            controller: 'footerController',
            link: function( scope, element){
            }
    };
});