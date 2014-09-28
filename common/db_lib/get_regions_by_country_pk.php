<?php
require('db_lib.php');
function get_regions_by_country_pk( $pk_country )
{
     $conn = db_connect();
     $query = <<<SQL
     			select *
     			  from tb_region
     			 where country = $1
SQL;

     pg_prepare( $conn, "query", $query );
     $result = pg_execute( $conn, "query", array( $pk_country ));
     if ( !$result ){
     	echo "An error occurred.\n";
     	exit;
     }
     $arr = pg_fetch_all( $result );
     return $arr;
}
?>