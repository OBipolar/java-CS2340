create or replace function check_personality_value()
returns trigger 
as $_$
     BEGIN
         
     END;
$_$ LANGUAGE plpgsql;
    
 create trigger tr_check_personality_value 
 before insert on tb_entity_personality 
    for each row 
execute check_personality_value();
