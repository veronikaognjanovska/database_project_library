-- Потребно да да направите најмалку 3 извештаи во вашата апликација.
-- -- Кај извештаите, освен корисноста ќе се оценува и сложеноста
-- -- (повеќе поврзувања, агрегациски функции).
-- --
-- -- Примери за извештаи кај апликација со банка би биле:
-- -- - Месечен обрт на банката, за секој месец поединечно
-- -- - Најважен клиент во дадена година (клиент со најголема сума на трансфери по година)
-- -- - Просечен датум на пристигање на плата по клиент

-- Најчесто изнајмувани книги по ред
-- Преглед позајмици во даден месец
-- од даден член вкупно позајмици
-- членoви позајмици во моменталниот месец

-- Најчесто изнајмувани книги по ред -mesec
select pregled.seriski_broj, count(distinct p) as count, pregled.naslov,pregled.broj_strani,pregled.avtor, pregled.objavena_na_nastan_na_datum
    from pregled_na_site_knigi_i_status as pregled
        full outer join pozajmica p on pregled.seriski_broj = p.seriski_broj
    group by pregled.seriski_broj,3,4,5,6
    order by 2 desc
;
-- Најчесто изнајмувани книги по месец
select najbarani_knigi_count.mesec, najbarani_knigi_count.seriski_broj,
        k.naslov,k.broj_strani,k.avtor,k.objavena_na_nastan_na_datum
    from (with fullCount(mesec,seriski_broj,count) as
            (
                select extract(month from sp.datum_pozajmuvanje) as mesec,sp.seriski_broj, count(*) as count
                from site_pozajmici as sp
                group by sp.seriski_broj, extract(month from sp.datum_pozajmuvanje)
            ),
            maxed (mesec,maxCount) as
            (
                select mesec,max(count)
                from fullCount
                group by mesec
            )
            select f.mesec,f.seriski_broj
            from fullCount f,maxed m
            where (f.mesec,f.count) = (m.mesec,m.maxCount)
            )
        as najbarani_knigi_count
    left join pregled_na_site_knigi k on k.seriski_broj= najbarani_knigi_count.seriski_broj
;


-- Преглед позајмици во даден месец
select sp.seriski_broj, sp.naslov ,sp.chlen, sp.vraboten, sp.status
    from site_pozajmici as sp
    where extract(month from sp.datum_pozajmuvanje)=extract(month from '2020-03-01'::timestamp)--now()
;


-- од даден член вкупно позајмици
select c.ime || ' ' || c.prezime as chlen, count(datum_pozajmuvanje)
    from pozajmica as poz
        join chlen as chl on poz.chlen_embg = chl.embg
        join chovek as c on chl.embg = c.embg
        join kniga as k on poz.seriski_broj = k.seriski_broj
    group by c.embg ,datum_pozajmuvanje
    order by 2 desc
;

-- членoви позајмици во моменталниот месец
select c.ime || ' ' || c.prezime as chlen, count(datum_pozajmuvanje)
    from chlen as chl
         join chovek as c on chl.embg = c.embg
        left outer join pozajmica as poz on poz.chlen_embg = chl.embg
        left outer join kniga as k on poz.seriski_broj = k.seriski_broj
    where extract(month from datum_pozajmuvanje)=extract(month from '2020-03-01'::timestamp)--now()
    group by c.embg,extract(month from datum_pozajmuvanje)
    order by 2 desc
;


-- просечно доцнење со враќање по член после 2 недели
with docniCount(embg,docniAvg) as
    (
        select p.chlen_embg, abs(avg(p.datum_pozajmuvanje-(case when p.datum_vrakjanje is not null then p.datum_vrakjanje else now()::date end))) as docni_za
        from pozajmica p
        group by p.chlen_embg
    )
select c.embg, c2.ime || ' ' || c2.prezime as chlen,
       c2.telefonski_broj as chlen_telefonski_broj,
       d.docniAvg * interval '1 day' as prosechno_docnenje
    from chlen c
    left join chovek c2 on c.embg = c2.embg
    left join docniCount d on c.embg = d.embg
;


