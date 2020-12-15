-- Фаза 4: Функционални барања и SQL прашалници
-- Потребно е да се дефинираат најмалку 4 функционални барања за вашиот проект.
-- Најмалку две да бидат погледи (приказ на релевантни резултати во идната апликација) и
-- најмалку две форми (пополнување на податоци кои ќе имаат ефект во базата).
-- Овие функционални барања треба да се имплементираат преку SQL прашалници.
-- Се препорачува да бидат суштински барања од апликацијата
-- (пример, ако е банка, да бидат пополнување на уплатница, месечен извод од сметка итн...)

-- ФОРМИ
-- Изнајмување книга – пополнување форма за додавање нова позајмица во база (моменталниот вработен ја пополнува формата)
-- Враќање на книга – пополнување форма за менување податоци за дадена позајмица во база
-- Зачленување на даден нов член  – пополнување форма за додавање нов член во база

-- ПОГЛЕДИ
-- Прегед на сите книги
--  книги i kolku se dostapni
-- Прегед на сите книги i broj kolku se dostapni moemntalno
-- Преглед на сите позајмици
-- Преглед на моментални сите позајмици



-- ФОРМИ

-- Изнајмување книга – пополнување форма за додавање нова позајмица во база (моменталниот вработен ја пополнува формата)
begin;
update primerok
    set status='NOT AVAILABLE'
    where seriski_broj='000001' and inventaren_broj='000006'and status='AVAILABLE';
insert into pozajmica
    (seriski_broj, inventaren_broj, chlen_EMBG, vraboten_EMBG, datum_vrakjanje, datum_pozajmuvanje, status)
    values
    ('000001','000006','0101999999001','0101999000001',NULL,now()::date,'ACTIVE');
-- rollback ;
commit;
select * from pozajmica;
select * from primerok;

-- Враќање на книга – пополнување форма за менување податоци за дадена позајмица во база
begin;
update pozajmica
    set status='CLOSED', datum_vrakjanje=now()::date
    where seriski_broj='000001' and inventaren_broj='000006' and
          chlen_embg='0101999999001' and vraboten_embg='0101999000001' and
          datum_pozajmuvanje='2020-12-13' and status='ACTIVE';
update primerok
    set status='AVAILABLE'
    where seriski_broj='000001' and inventaren_broj='000006'and status='NOT AVAILABLE';
commit;
select * from pozajmica;


-- Зачленување на даден нов член  – пополнување форма за додавање нов член во база
begin;
INSERT INTO chovek( embg,  ime,  prezime,  datum_na_ragjanje,  adresa_na_ziveenje, telefonski_broj )
values
    ('0601999999001','chlen 6' ,'chlen 6','1999-01-06','ulica 1','079060199');
INSERT INTO chlen( embg,  datum_na_zachlenuvanje) values
    ('0601999999001','2020-01-06');
commit ;
select * from chovek;
select * from chlen;



-- ПОГЛЕДИ
-- Прегед на сите книги
create view pregled_na_site_knigi as
    select k.seriski_broj, k.naslov, k.broj_strani, a.ime || ' ' ||a.prezime as avtor, n.datum as objavena_na_nastan_na_datum
    from kniga as k
        left join kniga_napishana_avtor as kna on kna.seriski_broj=k.seriski_broj
        left join avtor as a on a.avtor_id=kna.avtor_id
        left join nastan n on k.nastan_id = n.nastan_id
    ;
select * from pregled_na_site_knigi;


--  книги i kolku se dostapni
create view dostapnost_na_knigi as
    select seriski_broj,count(inventaren_broj) as VKUPNO,count(case  p.status when 'AVAILABLE' then 1 else null end) as AVAILABLE
    from  primerok p
    group by 1
    order by 1 asc ;
    ;
select * from dostapnost_na_knigi;


-- Прегед на сите книги i broj kolku se dostapni moemntalno
create view pregled_na_site_knigi_i_status as
    select k.seriski_broj, k.naslov, k.broj_strani,
        k.avtor, k.objavena_na_nastan_na_datum,
        d.VKUPNO,d.AVAILABLE
    from pregled_na_site_knigi as k
        join dostapnost_na_knigi d on k.seriski_broj = d.seriski_broj
   order by 1 asc ;
    ;
select * from pregled_na_site_knigi_i_status;

/*
create view pregled_na_site_knigi_i_status as
    select distinct k.seriski_broj, k.naslov, k.broj_strani,
        a.ime || ' ' ||a.prezime as avtor, n.datum as objavena_na_nastan_na_datum,
        d.VKUPNO,d.AVAILABLE
    from kniga as k
        left join kniga_napishana_avtor as kna on k.seriski_broj=kna.seriski_broj
        left join avtor as a on a.avtor_id=kna.avtor_id
        left join nastan n on k.nastan_id = n.nastan_id
        left join primerok p on k.seriski_broj = p.seriski_broj
        left join pozajmica poz on p.seriski_broj = poz.seriski_broj and p.inventaren_broj = poz.inventaren_broj
        left join dostapnost_na_knigi d on k.seriski_broj = d.seriski_broj
    group by 1,2,3,4,5
    order by 1 asc ;
    ;
select * from pregled_na_site_knigi_i_status;
*/
-- Преглед на сите позајмици
create view site_pozajmici as
    select c.ime || ' ' || c.prezime as chlen, c.telefonski_broj as chlen_telefonski_broj,
        chl.datum_na_zachlenuvanje,
        poz.seriski_broj, poz.inventaren_broj, k.naslov, a.ime || ' ' || a.prezime as avtor,
        poz.datum_pozajmuvanje,
        c2.embg as vraboten_embg,c2.ime || ' ' || c2.prezime as vraboten, c2.telefonski_broj as vraboten_telefonski_broj,
        v.datum_na_vrabotuvanje,
        poz.status
    from pozajmica as poz
        join chlen as chl on poz.chlen_embg = chl.embg
        join chovek as c on chl.embg = c.embg
        join kniga as k on poz.seriski_broj = k.seriski_broj
        left join kniga_napishana_avtor as kna on k.seriski_broj=kna.seriski_broj
        left join avtor as a on a.avtor_id=kna.avtor_id
        join vraboten v on poz.vraboten_embg = v.embg
        join chovek as c2 on v.embg = c2.embg
    ;
select * from site_pozajmici;


-- Преглед на моментални сите позајмици
create view momentalni_pozajmici as
    select chlen, chlen_telefonski_broj, datum_na_zachlenuvanje, seriski_broj, inventaren_broj, naslov, avtor, datum_pozajmuvanje, vraboten, vraboten_telefonski_broj, datum_na_vrabotuvanje
    from site_pozajmici poz
    where poz.status='ACTIVE'
    ;
select * from momentalni_pozajmici;


