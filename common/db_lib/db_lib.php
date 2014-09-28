<?php
require('../constants.php');
function db_connect( $connection_string=CONNECTION_STRING )
{
     static $conn;
     if (!isset($conn)){
	     $conn = pg_connect( $connection_string ) 
	     	or die( "Could not connect to server\n" );
     }

     return $conn;
}

?>
