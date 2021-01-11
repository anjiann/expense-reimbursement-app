drop schema if exists ers cascade;
create schema ers;
set schema 'ers';

create table reimbursement_status(
	"status_id" serial primary key,
	"reimb_status" text 
		check ("reimb_status" like 'Pending' or "reimb_status" like 'Approved' 
		or "reimb_status" like 'Denied') not null
);

create table reimbursement_type(
	"type_id" serial primary key,
	"reimb_type" text 
		check ("reimb_type" like 'Lodging' or "reimb_type" like 'Travel' 
		or "reimb_type" like 'Food' or "reimb_type" like 'Other') not null
);

create table user_roles(
	"role_id" serial primary key,
	"role" text 
		check ("role" like 'Employee' or "role" like 'Finance manager') not null
);


create table users(
	"user_id" serial primary key,
	"username" text unique not null,
	"password" text not null,
	"first_name" text not null,
	"last_name" text not null,
	"email" text unique not null,
	"role_id" int references user_roles("role_id") not null,
	unique("username", "email")
);


create table reimbursements(
	"reimb_id" serial primary key,
	"amount" numeric(12,2) not null,
	"submitted" timestamp not null,
	"resolved" timestamp,
	"description" text,
	--"receipt" bytea,
	"author_id" int references users("user_id") not null,
	"resolver_id" int references users("user_id"),
	"status_id" int references reimbursement_status("status_id") not null,
	"type_id" int references reimbursement_type("type_id") not null
);

-- ========== default data ========== 
insert into reimbursement_status("reimb_status") values('Approved'), ('Pending'), ('Denied');
insert into reimbursement_type("reimb_type") values('Lodging'), ('Travel'), ('Food'), ('Other');
insert into user_roles("role") values('Employee'), ('Finance manager');

-- =========== Employees ===========
insert into users("username", "password","first_name", "last_name", "email", "role_id")
	values('anjian', 'anjian','andy','jian','andyjian@gmail.com', 1);
insert into users ("username", "password","first_name", "last_name", "email", "role_id")
	values ('johndoe', 'johndoe', 'John', 'Doe', 'johndoe@gmail.com', 1);
insert into users ("username", "password","first_name", "last_name", "email", "role_id")
	values ('joesmith', 'joesmith', 'Joe', 'Smith', 'joesmith@gmail.com.com', 1);

-- =========== managers ========
insert into users ("username", "password","first_name", "last_name", "email", "role_id")
	values ('alecbatson', 'alecbatson', 'Alec', 'Batson', 'alecbatson@gmail.com', 2);
insert into users ("username", "password","first_name", "last_name", "email", "role_id")
	values ('nicfleury', 'nicfleury', 'Nicolas', 'Fleury', 'nicfleury@gmail.com', 2);

-- =========== reimbursements ===========
-- Pending
insert into reimbursements("amount", "submitted", "description", "author_id", "status_id", "type_id")
	values (10.00, '2020-12-25 15:00:00', 'Lorem ipsum dolor sit amet.', 1, 2, 2);

-- approved/denied
insert into reimbursements("amount", "submitted", "resolved", "description", "author_id", "resolver_id",
	"status_id", "type_id")
	values(10.00, '2020-12-16 12:00:00', '2020-12-16 13:00:00', 
		'Vestibulum aliquet velit libero, in.', 1, 4, 1, 3);
insert into reimbursements("amount", "submitted", "resolved", "description", "author_id", "resolver_id",
	"status_id", "type_id")
	values(10.00, '2020-11-30 8:00:00', '2020-11-30 10:00:00', 
		'Proin elementum urna lectus, condimentum.', 2, 4, 3, 1);
insert into reimbursements("amount", "submitted", "resolved", "description", "author_id", "resolver_id",
	"status_id", "type_id")
	values(10.00, '2020-11-11 10:00:00', '2020-11-12 12:00:00', 
		'Interdum et malesuada fames ac.', 3, 5, 1, 3);

select * from users;
select * from reimbursements;
select * from user_roles;
select * from reimbursement_type;
select * from reimbursement_status;