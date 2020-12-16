-- 9 tables

--	Chovek ( embg,  ime,  prezime,  datum_na_ragjanje,  adresa_na_ziveenje, telefonski_broj )
create table chovek (
    embg char(13),
    ime varchar(100) not null,
    prezime varchar(100) not null,
    datum_na_ragjanje date not null,
    adresa_na_ziveenje varchar(150) not null,
    telefonski_broj varchar(15) not null,
    constraint pk_chovek_embg primary key (embg)
    );

--	Chlen (  embg*(Chovek), datum_na_zachlenuvanje )
create table chlen (
    embg char(13),
    datum_na_zachlenuvanje date not null,
    constraint pk_chen_embg primary key (embg),
    constraint fk_chlen_embg_chovek foreign key (embg) references chovek(embg)
    );

--	Vraboten ( embg*(Chovek), datum_na_vrabotuvanje )
create table vraboten (
    embg char(13),
    datum_na_vrabotuvanje date not null,
    constraint pk_vraboten_embg primary key (embg),
    constraint fk_vraboten_embg_chovek foreign key (embg) references chovek(embg)
    );

-- Avtor ( avtor_id,  ime,  prezime,  godina_na_ragjanje )
create table avtor (
    avtor_id integer,
    ime varchar(100) not null,
    prezime varchar(100) not null,
    godina_na_ragjanje integer not null,
    constraint pk_avtor_id primary key (avtor_id)
    );

--	Nastan ( nastan_id,  datum, embg_vraboten_glaven*(Vraboten))
create table nastan (
    nastan_id integer,
    datum date not null,
    embg_vraboten_glaven char(13) not null,
    constraint pk_nastan_id primary key (nastan_id),
    constraint fk_embg_vraboten_glaven foreign key (embg_vraboten_glaven) references vraboten(embg)
    );

-- Kniga ( seriski_broj, naslov, broj_strani, nastan_id*(Nastan) )
create table kniga (
    seriski_broj integer,
    naslov varchar(100) not null,
    broj_strani integer,
    nastan_id integer,
    constraint pk_inventaren_broj primary key (seriski_broj),
    constraint fk_nastan_id foreign key (nastan_id) references nastan(nastan_id)
    );

--	Primerok (seriski_broj*(Kniga), inventaren_broj)
-- create domain STATUS_PRIMEROK
--     varchar(20)
--     check ( value in ('AVAILABLE','UNAVAILABLE'));
create table primerok (
    seriski_broj integer not null,
    inventaren_broj integer not null,
     status varchar(20) check ( status in ('AVAILABLE','UNAVAILABLE') ),
    constraint pk_primerok primary key (seriski_broj,inventaren_broj),
    constraint fk_seriski_broj foreign key (seriski_broj) references kniga(seriski_broj)
    );

--	Kniga_napishana_avtor (seriski_broj *(Kniga), avtor_id*(Avtor))
create table kniga_napishana_avtor (
    avtor_id integer not null,
    seriski_broj integer not null,
    constraint pk_kniga_napishana_avtor primary key (avtor_id,seriski_broj),
    constraint fk_seriski_broj_many foreign key (seriski_broj) references kniga(seriski_broj),
    constraint fk_avtor_id_many foreign key (avtor_id) references avtor(avtor_id)
    );

-- create domain STATUS_POZAJMICA
--     varchar(20)
--     check ( value in ('ACTIVE','CLOSED'));

--	Pozajmica (seriski_broj*(Kniga), inventaren_broj *(integer), chlen_EMBG*(Chovek), vraboten_EMBG*(Chovek), datum_vrakjanje, datum_pozajmuvanje, status)
create table pozajmica (
    seriski_broj integer not null,
    inventaren_broj integer not null,
    chlen_embg char(13) not null,
    vraboten_embg char(13) not null,
    datum_vrakjanje date,
    datum_pozajmuvanje date not null,
    status varchar(20) check ( status in ('ACTIVE','CLOSED') ),
    constraint pk_pozajmica primary key (seriski_broj,inventaren_broj,chlen_embg,vraboten_embg,datum_pozajmuvanje),
    constraint fk_primerok foreign key (seriski_broj,inventaren_broj) references primerok(seriski_broj,inventaren_broj),
    constraint fk_chlen_embg foreign key (chlen_embg) references chlen(embg),
    constraint fk_vraboten_embg foreign key (vraboten_embg) references vraboten(embg)
    );