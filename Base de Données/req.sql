--Q1
--set serveroutput on ;	
create OR replace procedure prescription (id_med in integer, id_pat in integer) IS
	x integer;
	list table_medic;
	nom_maladie varchar(50);
	BEGIN
		select list_medic , id_maladie
		into list, x
		from traitement 
		where id_medecin = id_med and id_patient=id_pat;
		
		select nom 
		into nom_maladie
		from maladie
		where id_maladie=x;
		DBMS_OUTPUT.PUT_LINE('maladie prescrit = '||nom_maladie);
		for i in (select l.* from table(list) l)
			loop
				DBMS_OUTPUT.PUT_LINE('medic prescrit = '||i.nom_medicament);
			end loop;
		EXCEPTION
			WHEN no_data_found THEN
			  dbms_output.put_line('No such customer!');
		   WHEN others THEN
			  dbms_output.put_line('Error!');		
	END;
/
execute	prescription(10,4);

--Q2
create or replace procedure tre (id_pat in integer, date_trait1 in date) is
	idt traitement.id_traitement%type;
	descript traitement.description%type;
	x integer;
	y varchar(50);
	CURSOR c1
	IS
		 SELECT t.id_traitement,t.description, id_patient
		 FROM traitement t
		 where id_patient=id_pat and 
		 (select ABS(to_date(date_trait1, 'DD-MM-YYYY') - to_date(t.date_trait, 'DD-MM-YYYY')) diff_hours from dual)<t.duree;
	BEGIN
		DBMS_OUTPUT.PUT_LINE('--------------------------------');
		open c1;
			loop
				FETCH c1 into idt, descript, x;
				EXIT WHEN c1%notfound;
				--DBMS_OUTPUT.PUT_LINE(idt);
				select nom_patient
				into y
				from patient
				where id_patient = x;
				DBMS_OUTPUT.PUT_LINE('nom patient = '||y);
				DBMS_OUTPUT.PUT_LINE('traitement en cours = '||descript);
			end loop;
		close c1;
	END;
/
execute tre(4,'05-09-2016');
	
--Q3
create or replace procedure listNbrEffect (id_medic in integer) IS
	nbr_eff integer;
	c_id effect_indesirable.description%type;
	CURSOR c1
	IS
		 SELECT e.description
		 FROM effect_indesirable e, table(e.list_effect_medic) m
		 WHERE m.id_medicament =id_medic;
	BEGIN
		select count(*) 
		into nbr_eff
		from effect_indesirable e, table(e.list_effect_medic) m 
		where m.id_medicament =id_medic;
		DBMS_OUTPUT.PUT_LINE('nbr effets indesirables = '||nbr_eff);
		DBMS_OUTPUT.PUT_LINE('--------------------------------');
		DBMS_OUTPUT.PUT_LINE('les decription des effets sont = ');
		IF  (nbr_eff <> 0) then
			open c1;
			loop
			FETCH c1 into c_id;
			EXIT WHEN c1%notfound;
				DBMS_OUTPUT.PUT_LINE(c_id);
			end loop;
			close c1;
		end IF;	
	END;
/
execute	listNbrEffect(3);

--Q4
create or replace procedure genereInteractions (id_medic in integer) IS
	nom_medic varchar(50);
	nom_medic2 varchar(50);
	--descrip varchar(50);
	med medicament.nom_medicament%type;
	id_med1 intercation.id_medicament1%type;
	id_med2 intercation.id_medicament2%type;
	descrip intercation.description%type;
	des intercation.description%type;
	CURSOR c1
	IS
		 SELECT id_medicament2 , description
		 FROM intercation
		 WHERE id_medicament1 =id_medic;
	BEGIN
		select nom_medicament
		into nom_medic
		from medicament 
		where id_medicament=id_medic;
		DBMS_OUTPUT.PUT_LINE('le nom de medicament = '||nom_medic);
		DBMS_OUTPUT.PUT_LINE('--------------------------------');
		DBMS_OUTPUT.PUT_LINE('les medicament provoque interactions avec '||nom_medic||' sont = ');
		open c1;
			loop
				FETCH c1 into id_med2, descrip;
				EXIT WHEN c1%notfound;
				
				DBMS_OUTPUT.PUT_LINE('id_medic ='||id_med2);
					select nom_medicament
					into med
					from medicament 
					where id_medicament=id_med2;
					DBMS_OUTPUT.PUT_LINE('nom de medicament'||med);
					DBMS_OUTPUT.PUT_LINE('intercation provoquée ='||descrip);
			end loop;
		close c1;
	END;
/
execute	genereInteractions(4);

--Q5
create or replace function poposmedic (id_mal in integer) 
	RETURN table_medic IS
	x integer;
	list_medicament table_medic;
	nom_maladie varchar(50);
	BEGIN
		select nom into nom_maladie
		from maladie where id_maladie=id_mal;
		DBMS_OUTPUT.PUT_LINE('le nom de la maladie = '||nom_maladie);
		select list_medic into list_medicament
		from traitement where id_maladie = id_mal;
		return list_medicament;
	END;
	/
	declare
	liste table_medic; 
	begin
	liste := poposmedic(12233);
     for r in (select l.* from table(liste) l)
		loop
			DBMS_OUTPUT.PUT_LINE('medic prescrit = '||r.nom_medicament);
		end loop;	
	END;
	/

--Q6 
create or replace procedure determinEffet (id_medic in integer) IS
	id1 sub_effect.id_effet_indes%type;
	des_eff_ind varchar(250);
	id substance_active.id_sub_active%type;
	CURSOR c1
	IS
		 SELECT s.id_sub_active
		 FROM substance_active s, table(s.list_sub_medic) l 
		 WHERE l.id_medicament =id_medic;
	BEGIN
		open c1;
			loop
				FETCH c1 into id;
				EXIT WHEN c1%notfound;
				DBMS_OUTPUT.PUT_LINE('id sub active ='||id);
				DBMS_OUTPUT.PUT_LINE('----------------------');
				select id_effet_indes
				into id1
				from sub_effect
				where id_sub_active=id;
				DBMS_OUTPUT.PUT_LINE('id iffet ='||id1);
				DBMS_OUTPUT.PUT_LINE('----------------------');
				select description
				into des_eff_ind
				from effect_indesirable
				where id_effet_indes = id1;
				DBMS_OUTPUT.PUT_LINE('description effet indesirable = '||des_eff_ind);
			end loop;
		close c1;
	END;
/
execute	determinEffet(8);

--Q7
create or replace procedure  medic_prescrit_medecin_dev is
	id_med traitement.id_medecin%type;
	id_medic medicament.id_medicament%type;
	nom_medic medicament.nom_medicament%type;
	nom_med medecin.nom_medecin%type;
	x integer;
	CURSOR c1
	IS
		SELECT m1.id_medicament, t.id_medecin
		FROM traitement t, table(t.list_medic) m1 
		WHERE m1.id_medicament NOT IN 
					(select m2.id_medicament 
					from medecin m0, table(m0.list_dev_medic) m2 
					where m0.id_medecin <> t.id_medecin);
	BEGIN
		
		open c1;
			loop
				FETCH c1 into id_medic,id_med;
				EXIT WHEN c1%notfound;
					x:=0;
					select count(*) into x from traitement t1, table(t1.list_medic) m3 where m3.id_medicament = id_medic;
					if (x=1)then
						select m4.nom_medicament
						into nom_medic
						from medicament m4 
						where m4.id_medicament=id_medic;
						DBMS_OUTPUT.PUT_LINE('medicament ='||nom_medic);
						select nom_medecin
						into nom_med
						from medecin 
						where id_medecin=id_med;
						DBMS_OUTPUT.PUT_LINE('medecin qui a developpé le medicament ='||nom_med);
						DBMS_OUTPUT.PUT_LINE('------------------------');
					end if;
			end loop;
		close c1;
	END;
/
execute medic_prescrit_medecin_dev();

--Q8
create or replace procedure  medic_prescrit_lab_dev is
	id_med traitement.id_medecin%type;
	id_medic medicament.id_medicament%type;
	nom_medic medicament.nom_medicament%type;
	nom_med medecin.nom_medecin%type;
	x integer;
	y integer;
	z varchar(250);
	CURSOR c1
	IS
		SELECT m1.id_medicament, t.id_medecin
		FROM traitement t, table(t.list_medic) m1 
		WHERE m1.id_medicament NOT IN 
					(select m2.id_medicament 
					from medecin m0, table(m0.list_dev_medic) m2 
					where m0.id_medecin <> t.id_medecin);
	BEGIN
		
		open c1;
			loop
				FETCH c1 into id_medic,id_med;
				EXIT WHEN c1%notfound;
					x:=0;
					select count(*) into x from traitement t1, table(t1.list_medic) m3 where m3.id_medicament = id_medic;
					if (x=1)then
						select m4.nom_medicament
						into nom_medic
						from medicament m4 
						where m4.id_medicament=id_medic;
						DBMS_OUTPUT.PUT_LINE('medicament ='||nom_medic);
						select id_laboratoire
						into y
						from medecin 
						where id_medecin=id_med;
						select nom_lab
						into z
						from laboratoire 
						where id_laboratoire=y;
						DBMS_OUTPUT.PUT_LINE('nom laboratoire ='||z);
						DBMS_OUTPUT.PUT_LINE('------------------------');
					end if;
			end loop;
		close c1;
	END;
/
execute medic_prescrit_lab_dev();

--Q9
create or replace procedure  maladie_propable(id_obs in integer, cars_pat in varchar) is
	x integer;
	y integer;
	z integer;
	nom_mal varchar(50);
	id integer;
	lis table_mal;
	
	CURSOR c1
	IS
		 SELECT id_patient
		 FROM patient
		 WHERE caract_patient = cars_pat;
	BEGIN
		select id_medecin
		into x
		FROM consultation 
		WHERE id_observation = id_obs;
		select id_maladie, id_patient
		into y,z
		FROM traitement 
		WHERE id_medecin = x;
		select m.nom
		into nom_mal
		FROM maladie m 
		WHERE m.id_maladie = y;
		DBMS_OUTPUT.PUT_LINE('maladie = '||nom_mal);
		open c1;
			loop
				FETCH c1 into id;
				EXIT WHEN c1%notfound;
				SELECT p.list_maladie
				into lis
				FROM patient p
				WHERE id_patient = id;
				for i in (select l.* from table(lis) l)
				loop
					DBMS_OUTPUT.PUT_LINE('maladie = '||i.nom);
				end loop;
			end loop;
		close c1;
	END;
/
execute maladie_propable(3,'vieux');
--Q10
create or replace procedure terer(id_pat in integer, date_trait1 in date, med_lis1 in table_medic) is
	idt traitement.id_traitement%type;
	idm medicament.id_medicament%type;
	listt traitement.list_medic%type;
	cnt integer;
	CURSOR c1
	IS
		 SELECT t.id_traitement,m.id_medicament, t.list_medic
		 into idt,idm,listt
		 FROM traitement t,table(t.list_medic) m 
		 where id_patient=id_pat and 
		 (select ABS(to_date(date_trait1, 'DD-MM-YYYY') - to_date(t.date_trait, 'DD-MM-YYYY')) diff_hours from dual)<t.duree;
	BEGIN
			open c1;
			loop
				FETCH c1 into idt,idm,listt;
				EXIT WHEN c1%notfound;
				for i in (select distinct l.* from table(listt) l)
				loop
					DBMS_OUTPUT.PUT_LINE('medicament ='||i.nom_medicament);
						for j in (select l1.* from table(med_lis1) l1)
							loop		
								select count(*) into cnt 
								from intercation 
								where (id_medicament1=i.id_medicament 
										and id_medicament2=j.id_medicament)
										or (id_medicament2=i.id_medicament and id_medicament1=j.id_medicament);
								if(cnt>=1) then
									DBMS_OUTPUT.PUT_LINE('on a  '||cnt||'  intercations entre = '||i.nom_medicament||' et : '||j.nom_medicament||'  en cours de traitement');
								end if;
							end loop;
					end loop;
			end loop;
			close c1;
	END;
/
execute terer(4,'05-09-2016',table_medic(medic(2 ,'OMEPRAZOLE ABBOTT 10 mg, gélule gastro-résistante'),medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')));
--start Bureau/req.sql;
