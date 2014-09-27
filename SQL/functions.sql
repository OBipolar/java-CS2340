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


CREATE OR REPLACE FUNCTION fn_new_album
(
    in_name       varchar,
    in_event      int4,
    in_entity     int4
)
returns integer as
 $_$
declare
    my_album integer;
begin
    my_album := nextval('sq_pk_album');

    insert into tb_album
                (
                    album,
                    name,
                    event,
                    creator,
                    created,
                    modifier,
                    modified
                )
                values
                (
                    my_album,
                    in_name,
                    in_event,
                    in_entity,
                    now(),
                    in_entity,
                    now()
                );
    
    return my_album;
end
 $_$
    language 'plpgsql';




CREATE OR REPLACE FUNCTION fn_new_album_file
(
    in_file    int4,
    in_album   int4
)
returns integer as
 $_$
declare
    my_album_file integer;
begin
    my_album_file := nextval('sq_pk_album_file');

    insert into tb_album_file
                (
                    album_file,
                    file,
                    album
                )
                values
                (
                    my_album_file,
                    in_file,
                    in_album
                );

    return my_album_file;
end
 $_$
    language 'plpgsql';




CREATE OR REPLACE FUNCTION fn_new_authentication_method
(
    in_label   varchar
)
returns integer as
 $_$
declare
    my_authentication_method integer;
begin
    my_authentication_method := nextval('sq_pk_authentication_method');

    insert into tb_authentication_method
                (
                    authentication_method,
                    label
                )
                values
                (
                    my_authentication_method,
                    in_label
                );

    return my_authentication_method;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_authentication_method
(
    in_label   varchar
)
returns integer as
 $_$
declare
    my_authentication_method integer;
begin
    select authentication_method
      into my_authentication_method
      from tb_authentication_method
     where label = in_label;

    return my_authentication_method;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_authentication_method
(
    in_label   varchar
)
returns integer as
 $_$
declare
    my_authentication_method integer;
begin
    select fn_get_authentication_method
           (
               in_label
           )
      into my_authentication_method;

    if my_authentication_method is null then
        select fn_new_authentication_method
               (
                    in_label
               )
          into my_authentication_method;
    end if;

    return my_authentication_method;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_new_city
(
    in_label    varchar,
    in_region   int4
)
returns integer as
 $_$
declare
    my_city integer;
begin
    my_city := nextval('sq_pk_city');

    insert into tb_city
                (
                    city,
                    label,
                    region
                )
                values
                (
                    my_city,
                    in_label,
                    in_region
                );

    
    return my_city;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_city
(
    in_label   varchar
)
returns integer as
 $_$
declare
    my_city integer;
begin
    select city
      into my_city
      from tb_city
     where label = in_label;

    return my_city;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_city
(
    in_label    varchar,
    in_region   int4
)
returns integer as
 $_$
declare
    my_city integer;
begin
    select fn_get_city
           (
               in_label
           )
      into my_city;

    if my_city is null then
        select fn_new_city
               (
                    in_label,
                    in_region
               )
          into my_city;
    end if;

    return my_city;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_city
(
    in_label    varchar,
    in_region   int4,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_city integer;
begin
    select fn_get_city
           (
               in_label
           )
      into my_city;

    if my_city is not null then
        if in_do_update then
            update tb_city
               set label = in_label,
                   region = in_region
             where city = my_city;
             
        end if;
    else
        select fn_new_city
               (
                    in_label,
                    in_region
               )
          into my_city;
    end if;

    return my_city;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_new_comment
(
    in_comment_text       text,
    in_commenter          int4,
    in_reply_to_comment   int4,
    in_draft              bool
)
returns integer as
 $_$
declare
    my_comment integer;
begin
    my_comment := nextval('sq_pk_comment');

    insert into tb_comment
                (
                    comment,
                    comment_text,
                    commenter,
                    reply_to_comment,
                    draft,
                    created,
                    modified
                )
                values
                (
                    my_comment,
                    in_comment_text,
                    in_commenter,
                    in_reply_to_comment,
                    in_draft,
                    now(),
                    now()
                );

    return my_comment;
end
 $_$
    language 'plpgsql';




CREATE OR REPLACE FUNCTION fn_new_compensation
(
    in_compensation_type   int4,
    in_value               int4,
    in_event               int4,
    in_receiver            int4,
    in_entity              int4
)
returns integer as
 $_$
declare
    my_compensation integer;
begin
    my_compensation := nextval('sq_pk_compensation');

    insert into tb_compensation
                (
                    compensation,
                    compensation_type,
                    value,
                    event,
                    receiver,
                    creator,
                    created
                )
                values
                (
                    my_compensation,
                    in_compensation_type,
                    in_value,
                    in_event,
                    in_receiver,
                    in_entity,
                    now()
                );

    return my_compensation;
end
 $_$
    language 'plpgsql';




CREATE OR REPLACE FUNCTION fn_new_compensation_type
(
    in_label    varchar,
    in_unit     varchar,
    in_parent   int4
)
returns integer as
 $_$
declare
    my_compensation_type integer;
begin
    my_compensation_type := nextval('sq_pk_compensation_type');

    insert into tb_compensation_type
                (
                    compensation_type,
                    label,
                    unit,
                    parent
                )
                values
                (
                    my_compensation_type,
                    in_label,
                    in_unit,
                    in_parent
                );

    return my_compensation_type;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_compensation_type
(
    in_label   varchar
)
returns integer as
 $_$
declare
    my_compensation_type integer;
begin
    select compensation_type
      into my_compensation_type
      from tb_compensation_type
     where label = in_label;

    return my_compensation_type;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_compensation_type
(
    in_label    varchar,
    in_unit     varchar,
    in_parent   int4
)
returns integer as
 $_$
declare
    my_compensation_type integer;
begin
    select fn_get_compensation_type
           (
               in_label
           )
      into my_compensation_type;

    if my_compensation_type is null then
        select fn_new_compensation_type
               (
                    in_label,
                    in_unit,
                    in_parent
               )
          into my_compensation_type;
    end if;

    return my_compensation_type;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_compensation_type
(
    in_label    varchar,
    in_unit     varchar,
    in_parent   int4,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_compensation_type integer;
begin
    select fn_get_compensation_type
           (
               in_label
           )
      into my_compensation_type;

    if my_compensation_type is not null then
        if in_do_update then
            update tb_compensation_type
               set label = in_label,
                   unit = in_unit,
                   parent = in_parent
             where compensation_type = my_compensation_type;
             
        end if;
    else
        select fn_new_compensation_type
               (
                    in_label,
                    in_unit,
                    in_parent
               )
          into my_compensation_type;
    end if;

    return my_compensation_type;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_new_country
(
    in_label            varchar,
    in_two_letter_iso   bpchar
)
returns integer as
 $_$
declare
    my_country integer;
begin
    my_country := nextval('sq_pk_country');

    insert into tb_country
                (
                    country,
                    label,
                    two_letter_iso
                )
                values
                (
                    my_country,
                    in_label,
                    in_two_letter_iso
                );

    return my_country;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_country
(
    in_label   varchar
)
returns integer as
 $_$
declare
    my_country integer;
begin
    select country
      into my_country
      from tb_country
     where label = in_label;

    return my_country;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_country
(
    in_label            varchar,
    in_two_letter_iso   bpchar
)
returns integer as
 $_$
declare
    my_country integer;
begin
    select fn_get_country
           (
               in_label
           )
      into my_country;

    if my_country is null then
        select fn_new_country
               (
                    in_label,
                    in_two_letter_iso
               )
          into my_country;
    end if;

    return my_country;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_country
(
    in_label            varchar,
    in_two_letter_iso   bpchar,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_country integer;
begin
    select fn_get_country
           (
               in_label
           )
      into my_country;

    if my_country is not null then
        if in_do_update then
            update tb_country
               set label = in_label,
                   two_letter_iso = in_two_letter_iso
             where country = my_country;
             
        end if;
    else
        select fn_new_country
               (
                    in_label,
                    in_two_letter_iso
               )
          into my_country;
    end if;

    return my_country;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_new_destination
(
    in_event                int4,
    in_tourist_attraction   int4,
    in_city                 int4,
    in_country              int4
)
returns integer as
 $_$
declare
    my_destination integer;
begin
    my_destination := nextval('sq_pk_destination');

    insert into tb_destination
                (
                    destination,
                    event,
                    tourist_attraction,
                    city,
                    country
                )
                values
                (
                    my_destination,
                    in_event,
                    in_tourist_attraction,
                    in_city,
                    in_country
                );

    return my_destination;
end
 $_$
    language 'plpgsql';




CREATE OR REPLACE FUNCTION fn_new_entity_personality
(
    in_entity        int4,
    in_personality   int4,
    in_value         int4
)
returns integer as
 $_$
declare
    my_entity_personality integer;
begin
    my_entity_personality := nextval('sq_pk_entity_personality');

    insert into tb_entity_personality
                (
                    entity_personality,
                    entity,
                    personality,
                    value
                )
                values
                (
                    my_entity_personality,
                    in_entity,
                    in_personality,
                    in_value
                );

    return my_entity_personality;
end
 $_$
    language 'plpgsql';




CREATE OR REPLACE FUNCTION fn_new_entity_type
(
    in_label   varchar
)
returns integer as
 $_$
declare
    my_entity_type integer;
begin
    my_entity_type := nextval('sq_pk_entity_type');

    insert into tb_entity_type
                (
                    entity_type,
                    label
                )
                values
                (
                    my_entity_type,
                    in_label
                );

    return my_entity_type;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_entity_type
(
    in_label   varchar
)
returns integer as
 $_$
declare
    my_entity_type integer;
begin
    select entity_type
      into my_entity_type
      from tb_entity_type
     where label = in_label;

    return my_entity_type;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_entity_type
(
    in_label   varchar
)
returns integer as
 $_$
declare
    my_entity_type integer;
begin
    select fn_get_entity_type
           (
               in_label
           )
      into my_entity_type;

    if my_entity_type is null then
        select fn_new_entity_type
               (
                    in_label
               )
          into my_entity_type;
    end if;

    return my_entity_type;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_new_entity_visitor
(
    in_entity    int4,
    in_visitor   int4,
    in_visited   timestamp
)
returns integer as
 $_$
declare
    my_entity_visitor integer;
begin
    my_entity_visitor := nextval('sq_pk_entity_visitor');

    insert into tb_entity_visitor
                (
                    entity_visitor,
                    entity,
                    visitor,
                    visited
                )
                values
                (
                    my_entity_visitor,
                    in_entity,
                    in_visitor,
                    in_visited
                );

    return my_entity_visitor;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_entity_visitor
(
    in_entity    int4,
    in_visited   timestamp,
    in_visitor   int4
)
returns integer as
 $_$
declare
    my_entity_visitor integer;
begin
    select entity_visitor
      into my_entity_visitor
      from tb_entity_visitor
     where entity = in_entity
       and visited = in_visited
       and visitor = in_visitor;

    return my_entity_visitor;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_entity_visitor
(
    in_entity    int4,
    in_visitor   int4,
    in_visited   timestamp
)
returns integer as
 $_$
declare
    my_entity_visitor integer;
begin
    select fn_get_entity_visitor
           (
               in_entity,
               in_visited,
               in_visitor
           )
      into my_entity_visitor;

    if my_entity_visitor is null then
        select fn_new_entity_visitor
               (
                    in_entity,
                    in_visitor,
                    in_visited
               )
          into my_entity_visitor;
    end if;

    return my_entity_visitor;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_new_event
(
    in_start_date               date,
    in_duration_days            int4,
    in_proposed_start_date      date,
    in_proposed_duration_days   int4,
    in_entity                   int4
)
returns integer as
 $_$
declare
    my_event integer;
begin
    my_event := nextval('sq_pk_event');

    insert into tb_event
                (
                    event,
                    start_date,
                    duration_days,
                    proposed_start_date,
                    proposed_duration_days,
                    creator,
                    created,
                    modifier,
                    modified
                )
                values
                (
                    my_event,
                    in_start_date,
                    in_duration_days,
                    in_proposed_start_date,
                    in_proposed_duration_days,
                    in_entity,
                    now(),
                    in_entity,
                    now()
                );

    return my_event;
end
 $_$
    language 'plpgsql';




CREATE OR REPLACE FUNCTION fn_new_event_entity
(
    in_event    int4,
    in_entity   int4
)
returns integer as
 $_$
declare
    my_event_entity integer;
begin
    my_event_entity := nextval('sq_pk_event_entity');

    insert into tb_event_entity
                (
                    event_entity,
                    event,
                    entity
                )
                values
                (
                    my_event_entity,
                    in_event,
                    in_entity
                );

    return my_event_entity;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_event_entity
(
    in_entity   int4,
    in_event    int4
)
returns integer as
 $_$
declare
    my_event_entity integer;
begin
    select event_entity
      into my_event_entity
      from tb_event_entity
     where entity = in_entity
       and event = in_event;

    return my_event_entity;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_event_entity
(
    in_event    int4,
    in_entity   int4
)
returns integer as
 $_$
declare
    my_event_entity integer;
begin
    select fn_get_event_entity
           (
               in_entity,
               in_event
           )
      into my_event_entity;

    if my_event_entity is null then
        select fn_new_event_entity
               (
                    in_event,
                    in_entity
               )
          into my_event_entity;
    end if;

    return my_event_entity;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_new_file
(
    in_file_type     int4,
    in_host          int4,
    in_path          varchar,
    in_destination   int4,
    in_entity        int4
)
returns integer as
 $_$
declare
    my_file integer;
begin
    my_file := nextval('sq_pk_file');

    insert into tb_file
                (
                    file,
                    file_type,
                    host,
                    path,
                    destination,
                    creator,
                    created
                )
                values
                (
                    my_file,
                    in_file_type,
                    in_host,
                    in_path,
                    in_destination,
                    in_entity,
                    now()
                );

    return my_file;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_file
(
    in_path   varchar
)
returns integer as
 $_$
declare
    my_file integer;
begin
    select file
      into my_file
      from tb_file
     where path = in_path;

    return my_file;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_file
(
    in_file_type     int4,
    in_host          int4,
    in_path          varchar,
    in_destination   int4,
    in_entity        int4
)
returns integer as
 $_$
declare
    my_file integer;
begin
    select fn_get_file
           (
               in_path
           )
      into my_file;

    if my_file is null then
        select fn_new_file
               (
                    in_file_type,
                    in_host,
                    in_path,
                    in_destination,
                    in_entity
               )
          into my_file;
    end if;

    return my_file;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_file
(
    in_file_type     int4,
    in_host          int4,
    in_path          varchar,
    in_destination   int4,
    in_entity        int4,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_file integer;
begin
    select fn_get_file
           (
               in_path
           )
      into my_file;

    if my_file is not null then
        if in_do_update then
            update tb_file
               set file_type = in_file_type,
                   host = in_host,
                   path = in_path,
                   destination = in_destination,
                   creator = in_entity
             where file = my_file;
             
        end if;
    else
        select fn_new_file
               (
                    in_file_type,
                    in_host,
                    in_path,
                    in_destination,
                    in_entity
               )
          into my_file;
    end if;

    return my_file;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_new_file_comment
(
    in_file      int4,
    in_comment   int4
)
returns integer as
 $_$
declare
    my_file_comment integer;
begin
    my_file_comment := nextval('sq_pk_file_comment');

    insert into tb_file_comment
                (
                    file_comment,
                    file,
                    comment
                )
                values
                (
                    my_file_comment,
                    in_file,
                    in_comment
                );

    return my_file_comment;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_file_comment
(
    in_comment   int4
)
returns integer as
 $_$
declare
    my_file_comment integer;
begin
    select file_comment
      into my_file_comment
      from tb_file_comment
     where comment = in_comment;

    return my_file_comment;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_file_comment
(
    in_file      int4,
    in_comment   int4
)
returns integer as
 $_$
declare
    my_file_comment integer;
begin
    select fn_get_file_comment
           (
               in_comment
           )
      into my_file_comment;

    if my_file_comment is null then
        select fn_new_file_comment
               (
                    in_file,
                    in_comment
               )
          into my_file_comment;
    end if;

    return my_file_comment;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_file_comment
(
    in_file      int4,
    in_comment   int4,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_file_comment integer;
begin
    select fn_get_file_comment
           (
               in_comment
           )
      into my_file_comment;

    if my_file_comment is not null then
        if in_do_update then
            update tb_file_comment
               set file = in_file,
                   comment = in_comment
             where file_comment = my_file_comment;
             
        end if;
    else
        select fn_new_file_comment
               (
                    in_file,
                    in_comment
               )
          into my_file_comment;
    end if;

    return my_file_comment;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_new_file_favorite
(
    in_file      int4,
    in_entity    int4
)
returns integer as
 $_$
declare
    my_file_favorite integer;
begin
    my_file_favorite := nextval('sq_pk_file_favorite');

    insert into tb_file_favorite
                (
                    file_favorite,
                    file,
                    entity,
                    created
                )
                values
                (
                    my_file_favorite,
                    in_file,
                    in_entity,
                    now()
                );

    return my_file_favorite;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_file_favorite
(
    in_entity   int4,
    in_file     int4
)
returns integer as
 $_$
declare
    my_file_favorite integer;
begin
    select file_favorite
      into my_file_favorite
      from tb_file_favorite
     where entity = in_entity
       and file = in_file;

    return my_file_favorite;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_file_favorite
(
    in_file      int4,
    in_entity    int4
)
returns integer as
 $_$
declare
    my_file_favorite integer;
begin
    select fn_get_file_favorite
           (
               in_entity,
               in_file
           )
      into my_file_favorite;

    if my_file_favorite is null then
        select fn_new_file_favorite
               (
                    in_file,
                    in_entity
               )
          into my_file_favorite;
    end if;

    return my_file_favorite;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_file_favorite
(
    in_file      int4,
    in_entity    int4,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_file_favorite integer;
begin
    select fn_get_file_favorite
           (
               in_entity,
               in_file
           )
      into my_file_favorite;

    if my_file_favorite is not null then
        if in_do_update then
            update tb_file_favorite
               set file = in_file,
                   entity = in_entity
             where file_favorite = my_file_favorite;
             
        end if;
    else
        select fn_new_file_favorite
               (
                    in_file,
                    in_entity
               )
          into my_file_favorite;
    end if;

    return my_file_favorite;
end
 $_$
    language 'plpgsql';

CREATE OR REPLACE FUNCTION fn_new_file_like
(
    in_file      int4,
    in_entity    int4
)
returns integer as
 $_$
declare
    my_file_like integer;
begin
    my_file_like := nextval('sq_pk_file_like');

    insert into tb_file_like
                (
                    file_like,
                    file,
                    entity,
                    created
                )
                values
                (
                    my_file_like,
                    in_file,
                    in_entity,
                    now()
                );

    return my_file_like;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_file_like
(
    in_entity   int4,
    in_file     int4
)
returns integer as
 $_$
declare
    my_file_like integer;
begin
    select file_like
      into my_file_like
      from tb_file_like
     where entity = in_entity
       and file = in_file;

    return my_file_like;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_file_like
(
    in_file      int4,
    in_entity    int4
)
returns integer as
 $_$
declare
    my_file_like integer;
begin
    select fn_get_file_like
           (
               in_entity,
               in_file
           )
      into my_file_like;

    if my_file_like is null then
        select fn_new_file_like
               (
                    in_file,
                    in_entity
               )
          into my_file_like;
    end if;

    return my_file_like;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_file_like
(
    in_file      int4,
    in_entity    int4,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_file_like integer;
begin
    select fn_get_file_like
           (
               in_entity,
               in_file
           )
      into my_file_like;

    if my_file_like is not null then
        if in_do_update then
            update tb_file_like
               set file = in_file,
                   entity = in_entity
             where file_like = my_file_like;
             
        end if;
    else
        select fn_new_file_like
               (
                    in_file,
                    in_entity
               )
          into my_file_like;
    end if;

    return my_file_like;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_new_file_type
(
    in_label    varchar,
    in_parent   int4
)
returns integer as
 $_$
declare
    my_file_type integer;
begin
    my_file_type := nextval('sq_pk_file_type');

    insert into tb_file_type
                (
                    file_type,
                    label,
                    parent
                )
                values
                (
                    my_file_type,
                    in_label,
                    in_parent
                );

    return my_file_type;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_file_type
(
    in_label   varchar
)
returns integer as
 $_$
declare
    my_file_type integer;
begin
    select file_type
      into my_file_type
      from tb_file_type
     where label = in_label;

    return my_file_type;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_file_type
(
    in_label    varchar,
    in_parent   int4
)
returns integer as
 $_$
declare
    my_file_type integer;
begin
    select fn_get_file_type
           (
               in_label
           )
      into my_file_type;

    if my_file_type is null then
        select fn_new_file_type
               (
                    in_label,
                    in_parent
               )
          into my_file_type;
    end if;

    return my_file_type;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_file_type
(
    in_label    varchar,
    in_parent   int4,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_file_type integer;
begin
    select fn_get_file_type
           (
               in_label
           )
      into my_file_type;

    if my_file_type is not null then
        if in_do_update then
            update tb_file_type
               set label = in_label,
                   parent = in_parent
             where file_type = my_file_type;
             
        end if;
    else
        select fn_new_file_type
               (
                    in_label,
                    in_parent
               )
          into my_file_type;
    end if;

    return my_file_type;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_new_host
(
    in_name                    varchar,
    in_authentication_method   int4,
    in_authentication_url      varchar,
    in_prefix_url              varchar
)
returns integer as
 $_$
declare
    my_host integer;
begin
    my_host := nextval('sq_pk_host');

    insert into tb_host
                (
                    host,
                    name,
                    authentication_method,
                    authentication_url,
                    prefix_url
                )
                values
                (
                    my_host,
                    in_name,
                    in_authentication_method,
                    in_authentication_url,
                    in_prefix_url
                );

    return my_host;
end
 $_$
    language 'plpgsql';




CREATE OR REPLACE FUNCTION fn_new_invite
(
    in_event         int4,
    in_description   text,
    in_entity        int4
)
returns integer as
 $_$
declare
    my_invite integer;
begin
    my_invite := nextval('sq_pk_invite');

    insert into tb_invite
                (
                    invite,
                    event,
                    description,
                    creator,
                    created
                )
                values
                (
                    my_invite,
                    in_event,
                    in_description,
                    in_entity,
                    now()
                );

    return my_invite;
end
 $_$
    language 'plpgsql';




CREATE OR REPLACE FUNCTION fn_new_invite_file
(
    in_file     int4,
    in_invite   int4
)
returns integer as
 $_$
declare
    my_invite_file integer;
begin
    my_invite_file := nextval('sq_pk_invite_file');

    insert into tb_invite_file
                (
                    invite_file,
                    file,
                    invite
                )
                values
                (
                    my_invite_file,
                    in_file,
                    in_invite
                );

    return my_invite_file;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_invite_file
(
    in_file     int4,
    in_invite   int4
)
returns integer as
 $_$
declare
    my_invite_file integer;
begin
    select invite_file
      into my_invite_file
      from tb_invite_file
     where file = in_file
       and invite = in_invite;

    return my_invite_file;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_invite_file
(
    in_file     int4,
    in_invite   int4
)
returns integer as
 $_$
declare
    my_invite_file integer;
begin
    select fn_get_invite_file
           (
               in_file,
               in_invite
           )
      into my_invite_file;

    if my_invite_file is null then
        select fn_new_invite_file
               (
                    in_file,
                    in_invite
               )
          into my_invite_file;
    end if;

    return my_invite_file;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_new_message
(
    in_subject            varchar,
    in_message_text       text,
    in_reply_to_message   int4,
    in_sender             int4,
    in_deleted            timestamp
)
returns integer as
 $_$
declare
    my_message integer;
begin
    my_message := nextval('sq_pk_message');

    insert into tb_message
                (
                    message,
                    subject,
                    message_text,
                    reply_to_message,
                    sender,
                    created,
                    modified,
                    deleted
                )
                values
                (
                    my_message,
                    in_subject,
                    in_message_text,
                    in_reply_to_message,
                    in_sender,
                    now(),
                    now(),
                    in_deleted
                );

    return my_message;
end
 $_$
    language 'plpgsql';




CREATE OR REPLACE FUNCTION fn_new_message_receiver
(
    in_message      int4,
    in_receiver     int4,
    in_notified     timestamp,
    in_email_sent   timestamp,
    in_received     timestamp
)
returns integer as
 $_$
declare
    my_message_receiver integer;
begin
    my_message_receiver := nextval('sq_pk_message_receiver');

    insert into tb_message_receiver
                (
                    message_receiver,
                    message,
                    receiver,
                    notified,
                    email_sent,
                    received
                )
                values
                (
                    my_message_receiver,
                    in_message,
                    in_receiver,
                    in_notified,
                    in_email_sent,
                    in_received
                );

    return my_message_receiver;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_message_receiver
(
    in_message    int4,
    in_receiver   int4
)
returns integer as
 $_$
declare
    my_message_receiver integer;
begin
    select message_receiver
      into my_message_receiver
      from tb_message_receiver
     where message = in_message
       and receiver = in_receiver;

    return my_message_receiver;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_message_receiver
(
    in_message      int4,
    in_receiver     int4,
    in_notified     timestamp,
    in_email_sent   timestamp,
    in_received     timestamp
)
returns integer as
 $_$
declare
    my_message_receiver integer;
begin
    select fn_get_message_receiver
           (
               in_message,
               in_receiver
           )
      into my_message_receiver;

    if my_message_receiver is null then
        select fn_new_message_receiver
               (
                    in_message,
                    in_receiver,
                    in_notified,
                    in_email_sent,
                    in_received
               )
          into my_message_receiver;
    end if;

    return my_message_receiver;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_message_receiver
(
    in_message      int4,
    in_receiver     int4,
    in_notified     timestamp,
    in_email_sent   timestamp,
    in_received     timestamp,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_message_receiver integer;
begin
    select fn_get_message_receiver
           (
               in_message,
               in_receiver
           )
      into my_message_receiver;

    if my_message_receiver is not null then
        if in_do_update then
            update tb_message_receiver
               set message = in_message,
                   receiver = in_receiver,
                   notified = in_notified,
                   email_sent = in_email_sent,
                   received = in_received
             where message_receiver = my_message_receiver;
             
        end if;
    else
        select fn_new_message_receiver
               (
                    in_message,
                    in_receiver,
                    in_notified,
                    in_email_sent,
                    in_received
               )
          into my_message_receiver;
    end if;

    return my_message_receiver;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_new_personality
(
    in_label       varchar,
    in_min_value   int4,
    in_max_value   int4
)
returns integer as
 $_$
declare
    my_personality integer;
begin
    my_personality := nextval('sq_pk_personality');

    insert into tb_personality
                (
                    personality,
                    label,
                    min_value,
                    max_value
                )
                values
                (
                    my_personality,
                    in_label,
                    in_min_value,
                    in_max_value
                );

    return my_personality;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_personality
(
    in_label   varchar
)
returns integer as
 $_$
declare
    my_personality integer;
begin
    select personality
      into my_personality
      from tb_personality
     where label = in_label;

    return my_personality;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_personality
(
    in_label       varchar,
    in_min_value   int4,
    in_max_value   int4
)
returns integer as
 $_$
declare
    my_personality integer;
begin
    select fn_get_personality
           (
               in_label
           )
      into my_personality;

    if my_personality is null then
        select fn_new_personality
               (
                    in_label,
                    in_min_value,
                    in_max_value
               )
          into my_personality;
    end if;

    return my_personality;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_personality
(
    in_label       varchar,
    in_min_value   int4,
    in_max_value   int4,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_personality integer;
begin
    select fn_get_personality
           (
               in_label
           )
      into my_personality;

    if my_personality is not null then
        if in_do_update then
            update tb_personality
               set label = in_label,
                   min_value = in_min_value,
                   max_value = in_max_value
             where personality = my_personality;
             
        end if;
    else
        select fn_new_personality
               (
                    in_label,
                    in_min_value,
                    in_max_value
               )
          into my_personality;
    end if;

    return my_personality;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_new_referral
(
    in_referrer        int4,
    in_email_address   varchar,
    in_email_sent      timestamp
)
returns integer as
 $_$
declare
    my_referral integer;
begin
    my_referral := nextval('sq_pk_referral');

    insert into tb_referral
                (
                    referral,
                    referrer,
                    email_address,
                    email_sent,
                    created
                )
                values
                (
                    my_referral,
                    in_referrer,
                    in_email_address,
                    in_email_sent,
                    now()
                );

    return my_referral;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_referral
(
    in_email_address   varchar,
    in_referrer        int4
)
returns integer as
 $_$
declare
    my_referral integer;
begin
    select referral
      into my_referral
      from tb_referral
     where email_address = in_email_address
       and referrer = in_referrer;

    return my_referral;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_referral
(
    in_referrer        int4,
    in_email_address   varchar,
    in_email_sent      timestamp
)
returns integer as
 $_$
declare
    my_referral integer;
begin
    select fn_get_referral
           (
               in_email_address,
               in_referrer
           )
      into my_referral;

    if my_referral is null then
        select fn_new_referral
               (
                    in_referrer,
                    in_email_address,
                    in_email_sent
               )
          into my_referral;
    end if;

    return my_referral;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_referral
(
    in_referrer        int4,
    in_email_address   varchar,
    in_email_sent      timestamp,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_referral integer;
begin
    select fn_get_referral
           (
               in_email_address,
               in_referrer
           )
      into my_referral;

    if my_referral is not null then
        if in_do_update then
            update tb_referral
               set referrer = in_referrer,
                   email_address = in_email_address,
                   email_sent = in_email_sent
             where referral = my_referral;
             
        end if;
    else
        select fn_new_referral
               (
                    in_referrer,
                    in_email_address,
                    in_email_sent
               )
          into my_referral;
    end if;

    return my_referral;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_new_region
(
    in_label     varchar,
    in_country   int4
)
returns integer as
 $_$
declare
    my_region integer;
begin
    my_region := nextval('sq_pk_region');

    insert into tb_region
                (
                    region,
                    label,
                    country
                )
                values
                (
                    my_region,
                    in_label,
                    in_country
                );

    return my_region;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_get_region
(
    in_label   varchar
)
returns integer as
 $_$
declare
    my_region integer;
begin
    select region
      into my_region
      from tb_region
     where label = in_label;

    return my_region;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_or_create_region
(
    in_label     varchar,
    in_country   int4
)
returns integer as
 $_$
declare
    my_region integer;
begin
    select fn_get_region
           (
               in_label
           )
      into my_region;

    if my_region is null then
        select fn_new_region
               (
                    in_label,
                    in_country
               )
          into my_region;
    end if;

    return my_region;
end
 $_$
    language 'plpgsql';


CREATE OR REPLACE FUNCTION fn_get_create_or_update_region
(
    in_label     varchar,
    in_country   int4,
    in_do_update    boolean default false
)
returns integer as
 $_$
declare
    my_region integer;
begin
    select fn_get_region
           (
               in_label
           )
      into my_region;

    if my_region is not null then
        if in_do_update then
            update tb_region
               set label = in_label,
                   country = in_country
             where region = my_region;
             
        end if;
    else
        select fn_new_region
               (
                    in_label,
                    in_country
               )
          into my_region;
    end if;

    return my_region;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_new_tag
(
    in_file       int4,
    in_tag_text   varchar,
    in_tagee      int4,
    in_tager      int4
)
returns integer as
 $_$
declare
    my_tag integer;
begin
    my_tag := nextval('sq_pk_tag');

    insert into tb_tag
                (
                    tag,
                    file,
                    tag_text,
                    tagee,
                    tager,
                    created
                )
                values
                (
                    my_tag,
                    in_file,
                    in_tag_text,
                    in_tagee,
                    in_tager,
                    now()
                );

    return my_tag;
end
 $_$
    language 'plpgsql';




CREATE OR REPLACE FUNCTION fn_new_tourist_attraction
(
    in_name     varchar,
    in_city     int4,
    in_region   int4,
    in_parent   int4,
    in_link     varchar,
    in_phone    varchar
)
returns integer as
 $_$
declare
    my_tourist_attraction integer;
begin
    my_tourist_attraction := nextval('sq_pk_tourist_attraction');

    insert into tb_tourist_attraction
                (
                    tourist_attraction,
                    name,
                    city,
                    region,
                    parent,
                    link,
                    phone
                )
                values
                (
                    my_tourist_attraction,
                    in_name,
                    in_city,
                    in_region,
                    in_parent,
                    in_link,
                    in_phone
                );

    return my_tourist_attraction;
end
 $_$
    language 'plpgsql';



CREATE OR REPLACE FUNCTION fn_new_wishlist
(
    in_entity               int4,
    in_tourist_attraction   int4,
    in_city                 int4,
    in_country              int4
)
returns integer as
 $_$
declare
    my_wishlist integer;
begin
    my_wishlist := nextval('sq_pk_wishlist');

    insert into tb_wishlist
                (
                    wishlist,
                    entity,
                    tourist_attraction,
                    city,
                    country
                )
                values
                (
                    my_wishlist,
                    in_entity,
                    in_tourist_attraction,
                    in_city,
                    in_country
                );

    
    return my_wishlist;
end
 $_$
    language 'plpgsql';



