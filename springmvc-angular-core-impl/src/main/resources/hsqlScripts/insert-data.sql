insert into security_role(name) values ('ROLE_ADMIN');
 
insert into security_role(name) values ('ROLE_OWNER');
 
insert into security_role(name) values ('ROLE_USER');
 
insert into user (first_name,family_name,password,username,confirm_password,active)
 
values ('Palko','Balazs','password','palika','password',1);
 
insert into user_security_role (user_id,security_role_id) values (1,1);