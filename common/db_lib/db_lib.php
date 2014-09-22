<?php
require('../constants.php');
function db_connect( $connection_string=CONNECTION_STRING )
{
     $conn = pg_connect( $connection_string );
     return $conn;
}

?>
