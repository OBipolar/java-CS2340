create sequence sq_pk_country;
create table tb_country
(
	country integer primary key default nextval('sq_pk_country'),
	label varchar(32) not null unique,
    two_letter_iso char(2) not null unique
);

create sequence sq_pk_region;
create table tb_region
(
	region 	integer primary key default nextval('sq_pk_region'),
	label 	varchar(32) not null unique key,
	country integer not null references tb_country 
);

create sequence sq_pk_city;
create table tb_city
(
	city  	integer primary key default nextval('sq_pk_city'),
	label 	varchar(32) not null unique key,
	region 	integer not null references tb_region
);

create sequence sq_pk_tourist_attraction;
create table tb_tourist_attraction
( 
	tourist_attraction 	integer primary key default nextval('sq_pk_tourist_attraction'),
	name 				varchar(64) not null unique key,
	city 				integer references tb_city,
	region 				integer references tb_region,
	parent 				integer references tb_tourist_attraction,
	link 				varchar(128),
	phone 				varchar(32),
	check ( city is not null or region is not null )
);

create table tb_entity_type
(
	entity_type 	integer primary key,
	label 			varchar(32) not null unique key
);

create sequence sq_pk_entity;
create table tb_entity
(
	entity 					integer primary key default('sq_pk_entity'),
	entity_type				integer references tb_entity_type,
	username 				varchar(128) not null unique,
	password_hash 			varchar(1024) not null,
	salt 					char(8) not null,
	reset_password_token 	varchar(32),
	email_address			varchar(128) unique,
	creator 				integer not null references tb_entity,
	created 				timestamp not null default now(),
	modifier				integer not null references tb_entity,
	modified 				timestamp not null default now()
);

create sequence sq_pk_wishlist;
create table tb_wishlist
(
	wishlist 			integer primary key default('sq_pk_wishlist'),
	entity 				integer not null references tb_entity,
	tourist_attraction 	integer references tb_tourist_attraction,
	city 				integer references tb_city,
	country 		 	integer references tb_country,
	check ( tourist_attraction is not null or city is not null or country is not null )
);

create sequence sq_pk_event;
create table tb_event
(
	event 					integer primary key default('sq_pk_event'),
	start_date 				date check ( start_date >= 'today'::date ),
	duration_days 	 		integer check ( duration_days > 0 ),
	proposed_start_date 	date not null check ( propsed_start_date >= 'today'::date ),
	proposed_duration_days 	integer check ( propsed_duration_days > 0 ),
	creator 				integer not null references tb_entity,
	created 				timestamp not null default now(),
	modifier				integer not null references tb_entity,
	modified 				timestamp not null default now()
);

create sequence sq_pk_destination;
create table tb_destination
(
	destination 			integer primary key default('sq_pk_destination'),
	event 					integer not null references tb_event,
	tourist_attraction		integer references tb_tourist_attraction,
	city 					integer references tb_city,
	country					integer references tb_country,
	check ( tourist_attraction is not null or city is not null or country is not null )
);

create sequence sq_pk_event_entity;
create table tb_event_entity
(
	event_entity			integer primary key default('sq_pk_event_entity'),
	event 					integer not null references tb_event,
	entity					integer not null references tb_entity,
	unique( event, entity )
);

create sequence sq_pk_compensation_type;
create table tb_compensation_type
(
	compensation_type 		integer primary key default('sq_pk_compensation_type'),
	label 					varchar(32) not null unique,
	unit					varchar(32) not null,
	parent 					integer references tb_compensation_type
);

create sequence sq_pk_compensation;
create table tb_compensation
(
	compensation			integer primary key default('sq_pk_compensation'),
	compensation_type 		integer not null references tb_compensation_type,
	value					integer,
	event 					integer references tb_event,
	receiver				integer not null references tb_entity,
	creator 				integer not null references tb_entity,
	created 				timestamp not null
);

create sequence sq_pk_personality;
create table tb_personality
(
	personality				integer primary key default('sq_pk_personality'),
	label 					varchar(64) not null unique,
	min_value 				integer,
	max_value				integer,
	check( 
			( 	min_value is not null 
			and max_value is not null 
			and min_value < max_value )
			or
			( 	min_value is null
			and max_value is null )
		 )
);

create sequence sq_pk_entity_personality;
create table tb_entity_personality
(
	entity_personality 		integer primary key default('sq_pk_entity_personality'),
	entity					integer not null references tb_entity,
	personality				integer not null references tb_personality,
	value 					integer
);

create sequence sq_pk_referral;
create table tb_referral
(
	referral 				integer primary key default('sq_pk_referral'),
	referrer 			    integer not null references tb_entity,
	email_address			varchar(128) not null,
	email_sent 				timestamp,
	created 				timestamp not null,
	unique( email_address, referrer )
);

create sequence sq_pk_message;
create table tb_message
(
	message 				integer primary key default('sq_pk_message'),
	subject					varchar(128),
	message_text 			text not null,
	reply_to_message        integer references tb_message,
	sender 					entity not null,
	created 				timestamp not null,
    modified                timestamp,
    deleted                 timestamp
);

create sequence sq_pk_message_receiver;
create table tb_message_receiver
(
	message_receiver 		integer primary key default('sq_pk_message_receiver'),
	message 				integer not null references tb_message,
	receiver 				integer not null references tb_entity,
	notified				timestamp,
	email_sent 				timestamp,
	received 				timestamp,
	unique( message, receiver )
);

create sequence sq_pk_entity_visitor;
create table tb_entity_visitor
(
	entity_visitor          integer primary key default('sq_pk_entity_visitor'),
    entity 					integer not null references tb_entity,
	visitor 				integer not null references tb_entity,
	visited 				timestamp not null,
	unique( entity, visitor, visited )
);	

create sequence sq_pk_invite;
create table tb_invite
(
	invite 					integer primary key default('sq_pk_invite'),
	event 					integer not null references tb_event,
	description 			text,
	creator 				integer not null references tb_entity,
	created 				timestamp not null
);

create sequence sq_pk_file_type;
create table tb_file_type
(
    file_type               integer primary key default('sq_pk_file_type'),
    label                   varchar(32) unique,
    parent                  integer references tb_file_type
);

create sequence sq_pk_authentication_method;
create table tb_authentication_method
(
    authentication_method   integer primary key default('sq_pk_authentication_method'),
    label                   varchar(32) unique
);

create sequence sq_pk_host;
create table tb_host
(
    host                    integer primary key default('sq_pk_host'),
    name                    varchar(32) not null,
    authentication_method   integer references tb_authentication_method,
    authentication_url      varchar(256),
    prefix_url              varchar(256) not null 
);

create sequence sq_pk_file;
create table tb_file
(
    file                    integer primary key default('sq_pk_file'),
    file_type               integer not null references tb_file_type,
    host                    integer not null references tb_host,
    path                    varchar(512) not null unique,
    destination             integer references tb_destination,
    creator                 integer not null references tb_entity,
    created                 timestamp not null 
);

create sequence sq_pk_invite_file;
create table tb_invite_file
(
    invite_file             integer primary key default('sq_pk_invite_file'),
    file                    integer not null references tb_file,
    invite                  integer not null references tb_invite,
    unique( file, invite )
);

create sequence sq_pk_file_like;
create table tb_file_like
(
    file_like               integer primary key default('sq_pk_file_like'),
    file                    integer not null references tb_file,
    entity                  integer not null references tb_entity,
    created                 timestamp not null,
    unique( file, entity )
);

create sequence sq_pk_file_favorite;
create table tb_file_favorite
(
    file_favorite           integer primary key default('sq_pk_file_favorite'),
    file                    integer not null references tb_file,
    entity                  integer not null references tb_entity,
    created                 timestamp not null,
    unique( file, entity )
);

create sequence sq_pk_comment;
create table tb_comment
(
    comment                 integer primary key default('sq_pk_comment'),
    comment_text            text not null,
    commenter               integer not null references tb_entity,
    reply_to_comment        integer references tb_comment,
    draft                   boolean not null default false,,
    created                 timestamp not null default now(),
    modified                timestamp not null default now()
);

create sequence sq_pk_file_comment;
create table tb_file_comment
(
     file_comment           integer primary key default('sq_pk_file_comment'),
     file                   integer not null references tb_file,
     comment                integer not null unique references tb_comment
);

create sequence sq_pk_album;
create table tb_album
(
     album                  integer primary key default('sq_pk_album'),
     name                   varchar(64),
     event                  integer references tb_event,
     creator                integer not null references tb_entity,
     created                timestamp not null,
     modifier               integer not null references tb_entity,
     modified               timestamp not null
);

create sequence sq_pk_album_file;
create table tb_album_file
(   
     album_file             integer primary key default('sq_pk_album_file'),
     file                   integer not null references tb_file,
     album                  integer not null references tb_album
);

create sequence sq_pk_tag;
create table tb_tag
(
     tag                    integer primary key default('sq_pk_tag'),
     file                   integer not null references tb_file,
     tag_text               varchar(128),
     tagee                  integer references tb_entity,
     tager                  integer not null references tb_entity,
     created                timestamp not null default now(),
     check( tagee is not null or tag_text is not null )
);

