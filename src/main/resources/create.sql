create table tb_address (id bigserial not null, bairro varchar(255), complemento varchar(255), localidade varchar(255), logradouro varchar(255), uf varchar(255), primary key (id));
create table tb_users (address_id bigint unique, id VARCHAR(36) not null, cep varchar(255), email varchar(255), firstname varchar(255), lastname varchar(255), password varchar(255), username varchar(255), primary key (id));
alter table if exists tb_users add constraint FK9j7so2w210152yxot4ny1go3b foreign key (address_id) references tb_address;
