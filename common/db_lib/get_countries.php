<?php
require('db_lib.php');
echo '<pre>',print_r(get_countries()),'</pre>';
function get_countries()
{
     $conn = db_connect();
     $query = <<<SQL
     			select label
     			  from tb_country
SQL;

     $result = pg_query( $conn, $query );
     if ( !$result ){
     	echo "An error occurred.\n";
     	exit;
     }
     $arr = pg_fetch_all( $result );
     return $arr;
}
?>