CREATE SEQUENCE seq_empr_sq_empr;
CREATE SEQUENCE seq_pess_sq_pess;
CREATE SEQUENCE seq_cate_sq_cate;
CREATE SEQUENCE seq_usua_sq_usua;
CREATE SEQUENCE seq_bema_sq_bema;
create sequence vend_sq_vend;


CREATE TABLE usuario (
    usua_sq_usuario numeric NOT NULL,
    usua_tx_login varchar(50) NOT NULL,
	usua_tx_email varchar(50) NOT NULL,
    usua_tx_senha varchar(100) NOT NULL,
	usua_tx_nome varchar(80) NOT NULL,
	usua_dt_timestamp timestamp not null
);

CREATE TABLE pessoa (
    pess_sq_pess numeric NOT NULL,
    pess_tx_nome varchar(80) NOT NULL,
    pess_nr_ddd varchar(2),
    pess_nr_telefone varchar(9),
    pess_nr_ddd_cel varchar(2),
    pess_nr_celular varchar(9),
    pess_tx_email varchar(100),
	usua_sq_usuario numeric NOT NULL,
	pess_dt_timestamp timestamp not null
);


CREATE TABLE categoria (
    cate_sq_cate numeric NOT NULL,
    cate_tx_descricao varchar(50) NOT NULL,
    usua_sq_usuario numeric NOT NULL,
	cate_dt_timestamp timestamp not null
);


CREATE TABLE bem_material (
    bema_sq_bema numeric NOT NULL,
    bema_tx_descricao varchar(100) NOT NULL,
	bema_tx_estado varchar(200),
    cate_sq_cate numeric NOT NULL,
	bema_dt_timestamp timestamp not null
);

CREATE TABLE emprestimos (
    empr_sq_empr numeric NOT NULL,
    pess_sq_pess numeric NOT NULL,
    bema_sq_bema numeric NOT NULL,
    empr_dt_saida date NOT NULL,
	empr_tx_estado_dev varchar(200),
    empr_dt_devolucao date,
	empr_dt_timestamp timestamp not null
);

create table venda(
	vend_sq_vend numeric not null,
	bema_sq_bema numeric not null,
	vend_vl_valor decimal not null,
	vend_dt_venda date not null,
	vend_tx_motivo character(100),
	pess_sq_pess numeric,
	vend_nm_comprador character(100)
);

ALTER TABLE pessoa       ADD CONSTRAINT pk_pess PRIMARY KEY (pess_sq_pess);
ALTER TABLE usuario      ADD CONSTRAINT pk_usua PRIMARY KEY (usua_sq_usuario);
ALTER TABLE categoria    ADD CONSTRAINT pk_cate PRIMARY KEY (cate_sq_cate);
ALTER TABLE bem_material ADD CONSTRAINT pk_bema PRIMARY KEY (bema_sq_bema);
ALTER TABLE emprestimos  ADD CONSTRAINT pk_empr PRIMARY KEY (empr_sq_empr);
ALTER TABLE categoria    ADD CONSTRAINT fk_usua_cate FOREIGN KEY (usua_sq_usuario) REFERENCES usuario(usua_sq_usuario);
ALTER TABLE pessoa       ADD CONSTRAINT fk_usua_pess FOREIGN KEY (usua_sq_usuario) REFERENCES usuario(usua_sq_usuario);
ALTER TABLE bem_material ADD CONSTRAINT fk_cate_bema FOREIGN KEY (cate_sq_cate) REFERENCES categoria(cate_sq_cate);
ALTER TABLE emprestimos  ADD CONSTRAINT fk_bema_empr FOREIGN KEY (bema_sq_bema) REFERENCES bem_material(bema_sq_bema);
ALTER TABLE emprestimos  ADD CONSTRAINT fk_pess_empr FOREIGN KEY (pess_sq_pess) REFERENCES pessoa(pess_sq_pess);
ALTER TABLE usuario      add constraint un_usua_tx_login unique(usua_tx_login);
ALTER TABLE pessoa       add constraint un_usua_pess unique(usua_sq_usuario,pess_tx_nome);
ALTER TABLE categoria    add constraint un_cate_usua unique(usua_sq_usuario,cate_tx_descricao);
alter table venda 		add constraint pk_vend primary key(vend_sq_vend);
alter table venda 		add constraint fk_pess_vend foreign key(pess_sq_pess) references pessoa(pess_sq_pess);
alter table venda 		add constraint fk_bema_vend foreign key(bema_sq_bema) references bem_material(bema_sq_bema);

GRANT ALL PRIVILEGES ON  seq_empr_sq_empr to public;
GRANT ALL PRIVILEGES ON  seq_pess_sq_pess to public;
GRANT ALL PRIVILEGES ON  seq_cate_sq_cate to public;
GRANT ALL PRIVILEGES ON  seq_usua_sq_usua to public;
GRANT ALL PRIVILEGES ON  seq_bema_sq_bema to public;
GRANT ALL PRIVILEGES ON  vend_sq_vend to public;
GRANT ALL PRIVILEGES ON  emprestimos to public;
GRANT ALL PRIVILEGES ON  bem_material to public;
GRANT ALL PRIVILEGES ON  categoria to public;
GRANT ALL PRIVILEGES ON  pessoa to public;
GRANT ALL PRIVILEGES ON  usuario to public;
GRANT ALL PRIVILEGES ON  venda to public;

/* drops
drop SEQUENCE seq_empr_sq_empr;
drop SEQUENCE seq_pess_sq_pess;
drop SEQUENCE seq_cate_sq_cate;
drop SEQUENCE seq_usua_sq_usua;
drop SEQUENCE seq_bema_sq_bema;
drop sequence vend_sq_vend;
drop table emprestimos;
drop table bem_material;
drop table categoria;
drop table pessoa;
drop table usuario;
drop table venda;
*/
