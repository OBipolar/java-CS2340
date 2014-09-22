-- Used for Perl Scripts
create or replace function fn_get_table_pk_col
(
    in_table_name   varchar
)
returns varchar as
 $_$
declare
    my_pk_col       varchar;
begin

    if not fn_table_exists(in_table_name)
        then return null;
    end if;

    select pat.attname
      into my_pk_col
      from pg_class pc
      join pg_attribute pat
        on pat.attrelid = pc.oid
      join pg_constraint pcn
        on pcn.conrelid = pc.oid
       and pcn.contype = 'p'
       and pcn.conkey[1] = pat.attnum
     where pc.relname = in_table_name;

    return my_pk_col;

end
 $_$
    language 'plpgsql';

create or replace function fn_table_exists
(
    in_table_name   varchar
)
returns  boolean as
 $_$
  begin

      perform *
         from information_schema.tables
        where table_schema = 'public'
          and table_name = in_table_name;

    if found
    then
        return true;
    else
        return false;
    end if;

end
 $_$
   language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_new_entity
(
    in_entity_type            int4,
    in_username               varchar,
    in_password_hash          varchar,
    in_salt                   bpchar,
    in_reset_password_token   varchar,
    in_email_address          varchar,
    in_entity                 int4
)
returns integer as
 $_$
declare
    my_entity integer;
begin
    my_entity := nextval('sq_pk_entity');

    insert into tb_entity
                (
                    entity,
                    entity_type,
                    username,
                    password_hash,
                    salt,
                    reset_password_token,
                    email_address,
                    creator,
                    created,
                    modifier,
                    modified
                )
                values
                (
                    my_entity,
                    in_entity_type,
                    in_username,
                    in_password_hash,
                    in_salt,
                    in_reset_password_token,
                    in_email_address,
                    in_entity,
                    now(),
                    in_entity,
                    now()
                );

    
    return my_entity;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_entity
(
    in_username   varchar
)
returns integer as
 $_$
declare
    my_entity integer;
begin
    select entity
      into my_entity
      from tb_entity
     where username = in_username;

    return my_entity;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_entity
(
    in_entity_type            int4,
    in_username               varchar,
    in_password_hash          varchar,
    in_salt                   bpchar,
    in_reset_password_token   varchar,
    in_email_address          varchar,
    in_entity                 int4
)
returns integer as
 $_$
declare
    my_entity integer;
begin
    select fn_get_entity
           (
               in_username
           )
      into my_entity;

    if my_entity is null then
        select fn_new_entity
               (
                    in_entity_type,
                    in_username,
                    in_password_hash,
                    in_salt,
                    in_reset_password_token,
                    in_email_address,
                    in_entity
               )
          into my_entity;
    end if;

    return my_entity;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_entity
(
    in_entity_type            int4,
    in_username               varchar,
    in_password_hash          varchar,
    in_salt                   bpchar,
    in_reset_password_token   varchar,
    in_email_address          varchar,
    in_entity                 int4,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_entity integer;
begin
    select fn_get_entity
           (
               in_username
           )
      into my_entity;

    if my_entity is not null then
        if in_do_update then
            update tb_entity
               set entity_type = in_entity_type,
                   username = in_username,
                   password_hash = in_password_hash,
                   salt = in_salt,
                   reset_password_token = in_reset_password_token,
                   email_address = in_email_address,
                   creator = in_entity,
                   modifier = in_entity,
                   modified = now()
             where entity = my_entity;
             
        end if;
    else
        select fn_new_entity
               (
                    in_entity_type,
                    in_username,
                    in_password_hash,
                    in_salt,
                    in_reset_password_token,
                    in_email_address,
                    in_entity
               )
          into my_entity;
    end if;

    return my_entity;
end
 $_$
    language 'plpgsql';


