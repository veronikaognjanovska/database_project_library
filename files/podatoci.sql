
INSERT INTO chovek( embg,  ime,  prezime,  datum_na_ragjanje,  adresa_na_ziveenje, telefonski_broj )
values
    ('0101999000001','vraboten 1' ,'vraboten 1','1999-01-01','ulica 1','070010199'),
    ('0201999000001','vraboten 2' ,'vraboten 2','1999-01-02','ulica 1','070020199'),
    ('0301999000001','vraboten 3' ,'vraboten 3','1999-01-03','ulica 1','070030199'),
    ('0401999000001','vraboten 4' ,'vraboten 4','1999-01-04','ulica 1','070040199'),
    ('0501999000001','vraboten 5' ,'vraboten 5','1999-01-05','ulica 1','070050199'),
    ('0101999999001','chlen 1' ,'chlen 1','1999-01-01','ulica 1','079010199'),
    ('0201999999001','chlen 2' ,'chlen 2','1999-01-02','ulica 1','079020199'),
    ('0301999999001','chlen 3' ,'chlen 3','1999-01-03','ulica 1','079030199'),
    ('0401999999001','chlen 4' ,'chlen 4','1999-01-04','ulica 1','079040199'),
    ('0501999999001','chlen 5' ,'chlen 5','1999-01-05','ulica 1','079050199');

INSERT INTO vraboten( embg, datum_na_vrabotuvanje ) values
     ('0101999000001','2020-01-01'),
     ('0201999000001','2020-01-01'),
     ('0301999000001','2020-01-01'),
     ('0401999000001','2020-01-01'),
     ('0501999000001','2020-01-01');

INSERT INTO chlen( embg,  datum_na_zachlenuvanje) values
     ('0101999999001','2020-01-01'),
     ('0201999999001','2020-01-02'),
     ('0301999999001','2020-01-03'),
     ('0401999999001','2020-01-04'),
     ('0501999999001','2020-01-05');

INSERT INTO avtor ( avtor_id,  ime,  prezime,  godina_na_ragjanje ) values
     ('000001','avtor 1' ,'avtor 1',1991),
     ('000002','avtor 2' ,'avtor 2',1991),
     ('000003','avtor 3' ,'avtor 3',1991),
     ('000004','avtor 4' ,'avtor 4',1991),
     ('000005','avtor 5' ,'avtor 5',1991);

INSERT INTO nastan ( nastan_id,  datum, embg_vraboten_glaven) values
     ('000001','2020-02-01' ,'0101999000001'),
     ('000002','2020-02-02' ,'0201999000001'),
     ('000003','2020-02-03' ,'0301999000001'),
     ('000004','2020-02-04' ,'0401999000001'),
     ('000005','2020-02-05' ,'0501999000001');

INSERT INTO kniga ( seriski_broj, naslov, broj_strani, nastan_id )values
    ('000001','naslov 1' ,'110','000001'),
    ('000002','naslov 2' ,'110','000002'),
    ('000003','naslov 3' ,'110','000003'),
    ('000004','naslov 4' ,'110','000004'),
    ('000005','naslov 5' ,'110','000005'),
    ('000006','naslov 1' ,'110',null);

-----++++---

INSERT INTO primerok (seriski_broj, inventaren_broj,status)values
    ('000001','000001','UNAVAILABLE'),
    ('000002','000002','AVAILABLE'),
    ('000003','000003','UNAVAILABLE'),
    ('000004','000004','AVAILABLE'),
    ('000005','000005','UNAVAILABLE'),
    ('000001','000006','AVAILABLE'),
    ('000001','000007','AVAILABLE'),
    ('000001','000008','AVAILABLE'),
    ('000004','000009','AVAILABLE'),
    ('000004','000010','AVAILABLE');

INSERT INTO kniga_napishana_avtor (seriski_broj, avtor_id)values
    ('000001','000001'),
    ('000002','000002'),
    ('000003','000003'),
    ('000004','000004'),
    ('000005','000005');

INSERT INTO pozajmica (seriski_broj, inventaren_broj, chlen_EMBG, vraboten_EMBG, datum_vrakjanje, datum_pozajmuvanje, status)values
    ('000001','000001','0101999999001','0101999000001',NULL,'2020-03-01','ACTIVE'),
    ('000002','000002','0201999999001','0101999000001','2020-04-22','2020-03-02','CLOSED'),
    ('000003','000003','0301999999001','0201999000001',NULL,'2020-03-03','ACTIVE'),
    ('000004','000004','0401999999001','0201999000001','2020-04-01','2020-03-04','CLOSED'),
    ('000005','000005','0501999999001','0301999000001',NULL,'2020-03-05','ACTIVE'),
    ('000001','000006','0101999999001','0101999000001','2020-04-01','2020-03-01','CLOSED'),
    ('000001','000007','0301999999001','0201999000001','2020-04-01','2020-03-03','CLOSED'),
    ('000001','000008','0501999999001','0301999000001','2020-04-01','2020-03-05','CLOSED');






select * from chovek;
select * from vraboten;
select * from chlen;

select * from avtor;
select * from nastan;
select * from kniga;

select * from pozajmica;
select * from primerok;
select * from kniga_napishana_avtor;



--
-- drop view  pregled_na_site_knigi_i_status;
-- drop view  momentalni_pozajmici;
-- drop view  site_pozajmici;
-- drop view  dostapnost_na_knigi;
-- drop view  pregled_na_site_knigi;
-- drop table pozajmica;
-- drop table kniga_napishana_avtor;
-- drop table primerok;
-- drop table kniga;
-- drop table nastan;
-- drop table avtor;
-- drop table vraboten;
-- drop table chlen;
-- drop table chovek;
-- drop domain STATUS_PRIMEROK;
-- drop domain STATUS_POZAJMICA;



