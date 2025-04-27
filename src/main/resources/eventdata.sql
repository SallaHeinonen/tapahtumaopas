insert into event_location(id, version, place, address, city) values(1, 1, 'Kylpylähotelli Kunnonpaikka', 'Jokiharjuntie 3', 'Vuorela');
insert into event_organizer(id, version, organization, phone) values(1, 1, 'Kunnonpaikka', '011 475 017');
insert into event_info(id, version, category) values(1, 1, 'Saunat');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)
values(1, 1, 'Savusaunailta', '2025-05-03', '15:00:00', '18:00:00', false, 1, 1, 1);

insert into event_location(id, version, place, address, city) values(2, 1, 'Pannuhuone Gust. Ranin', 'Kauppakatu 25', 'Kuopio');
insert into event_organizer(id, version, first_name, last_name, organization, phone, email) 
values(2, 1, 'Arto', 'Lankinen', 'Ravintolamestarit', '027 6330 231', 'ravintola@pannuhuone.net');
insert into event_info(id, version, category, description) 
values(2,1, 'Musiikkitapahtumat', 'Bändi soittaa ja sä laulat – Pannuhuone Gust. Raninin suosittu livekaraoke on paras tapa startata viikonloppu!
Toukokuusta elokuuhun livekaraokesta nautitaan Pannuhuoneen katetulla terassilla isommalla kokoonpanolla.');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id) 
values(2, 1, 'Livekaraoke', '2025-05-09', '20:00:00', '23:00:00', true, 2, 2, 2);

insert into event_location(id, version, place, address, city) values(3, 1, 'Kuopion kaupunginteatteri', 'Niiralankatu 2', 'Kuopio');
insert into event_organizer(id, version, phone, email) values(3,1, '0200 86100', 'teatteri@kuopio.fi');
insert into event_info(id, version, category, description)
values(3, 1, 'Teatterit', 'Yksi maailman parhaimpina pidetyistä musikaaleista Veriveljet saapuu Kuopion kaupunginteatterin lavalle!');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id) 
values(3, 1, 'Veriveljet', '2025-05-10', '18:00:00', '21:00:00', false, 3, 3, 3);

insert into event_location(id, version, place, address, city) 
values(4, 1, 'Kuopion tori', 'Tulliportinkatu 31', 'Kuopio');
insert into event_organizer(id, version, first_name, last_name, organization, phone, email) 
values(4, 1, 'Jyrki', 'Vuori', 'Kuopion kaupunki', '044 574 943', 'tapahtumat@kuopionkeskus.fi');
insert into event_info(id, version, category, description) 
values(4, 1, 'Kulttuuritapahtumat', 'Vappua vietetään perinteisin menoin Kuopion torilla torstaina 1. toukokuuta 2025! Ohjelmassa muun muassa livemusiikkia ja tanssia, vappupuheita, huutokauppa sekä lapsille ongintaa.');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id) 
values(4, 1, 'Vapputori', '2025-05-01', '09:00:00', '15:00:00', true, 4, 4, 4);

insert into event_location(id, version, place, address, city)
values(5, 1, 'VB-valokuvauskeskus', 'Asemakatu 3', 'Siilinjärvi');
insert into event_organizer(id, version, first_name, last_name, organization, phone, email)
values(5, 1, 'Joonas', 'Ahtikallio', 'Marginaali ry', '050 852 660', 'info@vb-valokuvakeskus.fi');
insert into event_info(id, version, category, description) 
values(5, 1, 'Kulttuuritapahtumat', 'VB-valokuvakeskuksessa järjestettävällä kasvikehitekurssilla tutustutaan kasvipohjaisiin kehitteisiin ja ekologiseen pimiötyöskentelyyn.');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)
values(5, 1, 'Kasvikehitekurssi', '2025-05-18', '09:00:00', '18:00:00', true, 5, 5, 5);

insert into event_location(id, version, place, address, city)
values(6, 1, 'Kylpylä- ja kuntokeskus Fontanella', 'Kuiluntie 2', 'Siilinjärvi');
insert into event_organizer(id, version, phone, email)
values(6, 1, '017 1200 462', 'myyntipalvelu@fontanella.fi');
insert into event_info(id, version, category, description) 
values(6, 1, 'Urheilutapahtumat', 'Fontanella avaa kesän uintikauden alennetuin lippuhinnoin. Lipun ostaneille tarjolla ilmaista jäätelöä tapahtuman ajan');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)
values(6, 1, 'Fontanella kesäkirmaus', '2025-05-15', '12:00:00', '18:00:00', false, 6, 6, 6);

insert into event_location(id, version, place, address, city)
values(7,1, 'Siilin ajorata', 'Oppipojantie 19', 'Siilinjärvi');
insert into event_organizer(id, version, first_name, last_name, organization, phone, email)
values(7,1, 'Elina', 'Hakonen', 'Siilinjärven kunta', '040 813 4451', 'elina.hakonen@gmail.com');
insert into event_info(id, version, category, description) 
values(7,1, 'Lasten tapahtumat', 'Siilin Rally on perheen nuorimmille tarkoitettu ilmaistapahtuma, jossa päästään miniralliautojen kyytiin');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)
values(7,1, 'Siilin Rally', '2025-05-11', '14:00:00', '20:00:00', true, 7, 7, 7);

insert into event_location(id, version, place, address, city)
values(8, 1, 'Kuopion musiikkikeskus', 'Kuopionlahdenkatu 23', 'Kuopio');
insert into event_organizer(id, version, first_name, last_name, organization, phone, email)
values(8,1, 'Heikki', 'Tammela', 'Taiteen edistämiskeskus', '040 411 297', 'heitammela@outlook.com');
insert into event_info(id, version, category) 
values(8,1, 'Musiikkitapahtumat');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)
values(8,1, 'Mozartin tunnetuimmat sävelmät', '2025-05-23', '19:00:00', '21:00:00', false, 8, 8, 8);

insert into event_location(id, version, place, address, city)
values(9, 1, 'Väinölänniemen uimaranta', 'Väinölänniemi 26', 'Kuopio');
insert into event_organizer(id, version, first_name, last_name, organization, phone, email)
values(9,1, 'Chris', 'Netto', 'Integralis Ry', '050 788 543' ,'nettochris@intergralis.com');
insert into event_info(id, version, category) 
values(9, 1, 'Opiskelijatapahtumat');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)
values(9, 1, 'Vänärin rantabileet', '2025-06-06', '17:00:00', '23:00:00', false, 9, 9, 9);

insert into event_location(id, version, place, address, city)
values(10, 1, 'Snellmaninpuisto', 'Kauppakatu 25', 'Kuopio');
insert into event_organizer(id, version, organization, phone)
values(10, 1, 'Opiskelijayhdistys Kupla ry', '0400 936 228');
insert into event_info(id, version, category, description) 
values(10, 1, 'Opiskelijatapahtumat', 'Tule viettämään mukavaa yhteistä aikaa picnicin (omat eväät), laulun, pelien ym. tekemisen muodossa muiden opiskelijoiden kanssa!');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)
values(10, 1, 'Puistositsit ja -sillis', '2020-05-16', '16:00:00', '21:00:00', true, 10, 10, 10);

insert into event_location(id, version, place, address, city)
values(11, 1, 'Tahkovuori', 'Tahkomäentie 125', 'Nilsiä');
insert into event_organizer(id, version, phone, email)
values(11, 1, '017 481 300', 'booking@tahko.com');
insert into event_info(id, version, category, description) 
values(11, 1, 'Urheilutapahtumat', '');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)
values(11, 1, 'Tahko Loppet', '2025-05-19', '10:00:00', '20:00:00', true, 11, 11, 11);

insert into event_location(id, version, place, address, city)
values(12, 1, 'Original Sokos Hotel Puijonsarvi', 'Minna Canthin katu 16', 'Kuopio');
insert into event_organizer(id, version, phone)
values(12, 1, '+358 10 7629 500');
insert into event_info(id, version, category, description)
values(12, 1, 'Ruokatapahtumat', 'Chaine des Rotisseurs – kilpipäivää juhlistetaan Puijonsarvessa Chardonnay-viinitastingilla!');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)
values(12, 1, 'Chardonnay-tasting', '2025-05-24', '15:00:00', '17:00:00', false, 12, 12, 12);

insert into event_location(id, version, place, address, city)
values(13, 1, 'Illuusioteatteri Taikavuori', 'Lusperinkuja 11', 'Nilsiä');
insert into event_organizer(id, version, first_name, last_name, phone, email)
values(13, 1, 'Jorma', 'Airaksinen', '050 525 8167', 'jorma.airaksinen@taikavuori.fi');
insert into event_info(id, version, category, description) 
values(13, 1, 'Lasten tapahtumat', 'Maailmanluokan koko perheen taikanäytös ainutlaatuisessa taikateatterissa Nilsiässä!');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)
values(13, 1,'Tuhat ja yksi ihmettä', '2025-06-14', '17:00:00', '18:30:00', false, 13, 13, 13);

insert into event_location(id, version, place, address, city)
values(14, 1,'Apteekkari Kuopio', 'Vuorikatu 24', 'Kuopio');
insert into event_organizer(id, version, organization, phone)
values(14, 1, 'Funkkaajat ry', '0504024340');
insert into event_info(id, version, category) 
values(14, 1, 'Musiikkitapahtumat');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)
values(14, 1, 'Techo Party', '2025-06-14', '20:00:00', '00:00:00', false, 14, 14, 14);

insert into event_location(id, version, place, address, city)
values(15,1, 'Kylpylähotelli Rauhalahti', 'Katiskaniementie 8', 'Kuopio');
insert into event_organizer(id, version, phone)
values(15,1,'030 608 3100');
insert into event_info(id, version, category) 
values(15,1, 'Tanssit');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)
values(15,1, 'Päivätanssit: Sanna Linna & Jake Niska Band', '2025-06-08', '15:00:00', '19:30:00', false, 15, 15, 15);

insert into event_location(id, version, place, address, city) values(16,1, 'Suomen ortodoksinen kirkkomuseo', 'Karjalankatu 1', 'Kuopio');
insert into event_organizer(id, version, phone, email) values(16,1, '0206100266', 'info@riisa.fi');
insert into event_info(id, version, category, description)values(16,1, 'Näyttelyt', 'Valokuvia Kuopion ortodoksisesta historiasta ja nykyhetkestä. Näyttely on osa Kuopio250-juhlavuotta.');
insert into event(id, version, name, date, start_time, end_time, free, eventlocation_id, eventorganizer_id, eventinfo_id)values(16, 1, 'Ortodoksien pääkaupunki', '2025-06-16', '09:00:00', '18:00:00', false, 16, 16, 16);