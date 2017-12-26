--Creation des tables
DROP TABLE maladie cascade constraint;
DROP TABLE patient cascade constraint;
DROP TABLE traitement cascade constraint;
DROP TABLE consultation cascade constraint;
DROP TABLE observation cascade constraint;
DROP TABLE laboratoire cascade constraint;
DROP TABLE medecin cascade constraint;
DROP TABLE intercation cascade constraint;
DROP TABLE medicament cascade constraint;
DROP TABLE sub_effect cascade constraint;
DROP TABLE substance_active cascade constraint;
DROP TABLE effect_indesirable cascade constraint;
DROP TABLE contre_indic cascade constraint;
DROP TABLE indication cascade constraint;
DROP type mal force;
DROP type table_mal force;
DROP type medic force;
DROP type table_medic force;


--table maladie
create type mal as object(
		id_maladie integer, 
		nom varchar2(250), 
		description varchar(250), 
		type_maladie integer
	);
	/
	create table maladie of mal( id_maladie primary key);
	
--table patient
create type table_mal as table of mal;
	/
	create table patient( 
		id_patient integer,  
		nom_patient varchar2(50), 
		prenom_patient varchar2(50), 
		date_N_patient date,
		caract_patient varchar2(50),
		list_maladie table_mal,
	constraint pk_patient primary key (id_patient)
	)
	nested table list_maladie store as maladieTable;
	
--table medicament
	create type medic as object(
		id_medicament integer, 
		nom_medicament varchar2(250)
	);
	/
	create table medicament of medic(id_medicament primary key);
	
	create type table_medic as table of medic;
	/

--table observation
	create table observation( 
		id_observation integer, 
		description varchar2(250),
	constraint pk_obs primary key (id_observation)
	);

--table laboratoire
	create table laboratoire( 
		id_laboratoire integer,
		nom_lab varchar2(250),
	constraint pk_lab primary key (id_laboratoire)
	);
	
--table medecin
	create table medecin( 
		id_medecin integer, 
		nom_medecin varchar2(50), 
		prenom_medecin varchar2(50), 
		date_N_med date,
		id_laboratoire integer,
		list_dev_medic table_medic,
	constraint pk_medecin primary key (id_medecin),
	constraint fk_medecin foreign key(id_laboratoire) references laboratoire(id_laboratoire) on delete cascade 
	)
	nested table list_dev_medic store as devmedicTable;
	
--table consultation
	create table consultation ( 
		id_consult integer, 
		d_consulatation date, 
		h_consulatation varchar2(10), 
		id_medecin integer, 
		id_observation integer,
	constraint pk_consult primary key (id_consult), 
	constraint fk_consult_med foreign key (id_medecin) references medecin(id_medecin) on delete cascade,
	constraint fk_consult_obs foreign key (id_observation) references observation(id_observation) on delete cascade
	);

--table traitement 

	
	create table traitement( 
		id_traitement integer, 
		description varchar2(250), 
		duree integer, 
		date_trait date,
		id_medecin integer, 
		id_patient integer,	
		id_maladie integer, 
		list_medic table_medic,
	constraint pk_traitement primary key (id_traitement),
	constraint fk_trait_med foreign key (id_medecin) references medecin(id_medecin) on delete cascade,
	constraint fk_trait_pat foreign key (id_patient) references patient(id_patient) on delete cascade,
	constraint fk_trait_mal foreign key (id_maladie) references maladie(id_maladie) on delete cascade
	)
	nested table list_medic store as medicTable;
		
--table intercation
	create table intercation ( 
		id_interaction integer, 
		id_medicament1 integer, 
		id_medicament2 integer, 
		description varchar2(250),
	constraint pk_intercation primary key (id_interaction),
	constraint fk_inter_medi1 foreign key (id_medicament1) references medicament(id_medicament) on delete cascade ,
	constraint fk_inter_medi2 foreign key (id_medicament2) references medicament(id_medicament) on delete cascade 
	);
	
--table substance active
	create table substance_active( 
		id_sub_active integer,
		description varchar2(250),
		list_sub_medic table_medic, 
	constraint pk_sub primary key (id_sub_active)
	)
	nested table list_sub_medic store as sub_medicTable;
	
--table effect indesirable
	create table effect_indesirable( 
		id_effet_indes integer, 
		description varchar2(250), 
		list_effect_medic table_medic,
	constraint pk_eff_indes primary key (id_effet_indes)
	)
	nested table list_effect_medic store as effect_medicTable;
	
--table substance active effect
	create table sub_effect( 
		id_sub_effect integer, 
		id_sub_active integer, 
		id_effet_indes integer,
	constraint pk_sub_effect primary key (id_sub_effect), 
	constraint fk_sub foreign key (id_sub_active) references substance_active(id_sub_active) on delete cascade,
	constraint fk_effect foreign key (id_effet_indes) references effect_indesirable(id_effet_indes) on delete cascade
	);
	
--table contre indication
	create table contre_indic( 
		id_contre_ind integer,
		description varchar2(250), 
		list_cindic_medic table_medic,
	constraint pk_contre_ind primary key (id_contre_ind)
	)
	nested table list_cindic_medic store as cindic_medicTable;
	
--table indication
	create table indication( 
		id_indication integer,
		description varchar2(250), 
		list_indic_medic table_medic,
	constraint pk_ind primary key (id_indication)
	)
	nested table list_indic_medic store as indic_medicTable;
	
-------------------------------------------------------------------------------remplissage table maladie
--Insertion dans la table maladie
insert into maladie values(1,'Maladie de l appareil digestif','les maladies qui touche l appareil digestif',0); 
insert into maladie values(11,'Maladie de l estomac','les maladies qui touchent l estomac',1); 
insert into maladie values(12,'Maladies du foie','Les maladies qui touchent le foie',1); 
insert into maladie values(13,'Maladies du colon','Les maladie qui touche le colon',1);
 
--Insertion des maladie en relation avec l'estomat
insert into maladie values(111,'Gastrite','Les gastrites',11);
insert into maladie values(112,'Ulsaire','Les ulsaires',11);

--Insertion des maladie en relation avec le foie
insert into maladie values(121,'cirrhose du foie','cirrhose du foie',12);
insert into maladie values(122,'Hepatite','Les Hepatites',12);
insert into maladie values(123,'Tuberculose hepatique','Tuberculose hepatique',12);

--Insertion des maladie en relation avec le colon
insert into maladie values(131,'Crhon','Crhon',13);
insert into maladie values(132,'Colon héritable','Colon héritable',13);
	
--Insertion des maladie en relation avec l'hepatite 
insert into maladie values(1221,'Hepatite alcoolique','Les Hepatites',122);
insert into maladie values(1222,'Hepatite chronique','Les Hepatites',122);
insert into maladie values(1223,'Hepatite virales humaines','Les Hepatites',122);
	
--Insertion des hepatites virales humaines
insert into maladie values(12231,'Hepatite A','Les Hepatites',1223);
insert into maladie values(12232,'Hepatite B','Les Hepatites',1223);
insert into maladie values(12233,'Hepatite C','Les Hepatites',1223);

-------------------------------------------------------------------------------remplissage table patient
insert into patient values(1 , 'Marc','Alex','09-09-1950','vieux',
								table_mal(
								mal(111,'Gastrite','Les gastrites',11),
								mal(121,'cirrhose du foie','cirrhose du foie',12),
								mal(1221,'Hepatite alcoolique','Les Hepatites',122)
										)
						);
						
insert into patient values(2 , 'jemy','Alice','10-10-1990','jeune',
								table_mal(
								mal(122,'Hepatite','Les Hepatites',12),
								mal(132,'Colon héritable','Colon héritable',13)
										)
						);
						
insert into patient values(3 , 'jerimie','bob','11-11-1991','Fragil',
								table_mal(
								mal(123,'Tuberculose hepatique','Tuberculose hepatique',12),
								mal(131,'Crhon','Crhon',13),
								mal(1222,'Hepatite chronique','Les Hepatites',122),
								mal(12231,'Hepatite A','Les Hepatites',1223)
										)
						);
						
insert into patient values(4 , 'cady','steven','12-12-2016','bebe',
								table_mal(
								mal(112,'Ulsaire','Les ulsaires',11),
								mal(12233,'Hepatite C','Les Hepatites',1223)
										)
						);
-------------------------------------------------------------------------------remplissage table medicament 
insert into medicament values(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg');
insert into medicament values(2 ,'OMEPRAZOLE ABBOTT 10 mg, gélule gastro-résistante');
insert into medicament values(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé');
insert into medicament values(4 ,'ALDACTONE 50 mg, comprimé sécable');
insert into medicament values(5 ,'CORTANCYL 20 mg, comprimé sécable');
insert into medicament values(6 ,'CELESTENE 4 mg/1 ml, solution injectable');
insert into medicament values(7 ,'HAVRIX 1440');
insert into medicament values(8 ,'BARACLUDE 0,05 mg/ml');
insert into medicament values(9 ,'SOVALDI 400 mg, comprimé pelliculé');

-------------------------------------------------------------------------------remplissage table laboratoire
insert into laboratoire values(1 ,'3M SANTE');		
insert into laboratoire values(2 ,'ABBOTT France');	
insert into laboratoire values(3 ,'GROUPE ACTELION LTD');	
insert into laboratoire values(4,'GROUPE ASEPTA LABORATOIRES, MONACO');	
insert into laboratoire values(5 ,'ADP LABORATOIRE PHARMACEUTIQUE ');	
insert into laboratoire values(6 ,'AELSLIFE ');	
insert into laboratoire values(7 ,'GROUPE AGUETTANT SANTÉ, FRANCE ');	
insert into laboratoire values(8 ,'ALLERGAN France SAS ');	
insert into laboratoire values(9 ,'ALLOGA France');	
insert into laboratoire values(10 ,'GROUPE AMGEN, USA ');

-------------------------------------------------------------------------------remplissage table medecin
insert into medecin values(1 ,'ALLON ','LEVY','12-09-1970',1,
								table_medic(
									medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé')
										)
						);
						
insert into medecin values(2 ,'BACARD','HUGO','12-07-1971',2,
								table_medic(
									medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
									medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé')
										)
						);
insert into medecin values(3 ,'BAKER ','MATTHEW','12-11-1972',6,
								table_medic(
									medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé'),
									medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')
										)
						);
insert into medecin values(4 ,'BALWE','CHETAN','12-10-1973',8,
								table_medic(
									medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
									medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé'),
									medic(7 ,'HAVRIX 1440')
										)
						);
insert into medecin values(5 ,'BELAIR','LUC','12-11-1974',7,
								table_medic(
									medic(4 ,'ALDACTONE 50 mg, comprimé sécable'),
									medic(5 ,'CORTANCYL 20 mg, comprimé sécable')
										)
						);	
insert into medecin values(6 ,'CEBALLOS','CESAR','12-12-1975',NULL,NULL);
						
insert into medecin values(7 ,'CHRISTIE','AARON','19-01-1961',2,
								table_medic(
									medic(5 ,'ADP LABORATOIRE PHARMACEUTIQUE '),
									medic(8 ,'ALLERGAN France SAS ')
										)
						);
insert into medecin values(8 ,'FANTINI','ARNO','19-08-1967',2,
								table_medic(
									medic(2 ,'OMEPRAZOLE ABBOTT 10 mg, gélule gastro-résistante')
										)
						);
insert into medecin values(9 ,'GARAY-LOPEZ ','LUIS','19-09-1968',7,NULL);
insert into medecin values(10 ,'GILES','FLORES','19-07-1980',7,
								table_medic(
									medic(7 ,'HAVRIX 1440'),
									medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable')
										)
						);
insert into medecin values(11 ,'ERIKSSON ','DENNIS','19-01-1955',NULL,NULL);

-------------------------------------------------------------------------------remplissage table interaction
insert into intercation values(1 ,9 ,5 ,'Interaction, ces medicament peuvent avoir des effets negatifs ensemble');
insert into intercation values(2 ,2 ,8 ,'Ces deux medicament ne sont pas compatible');
insert into intercation values(3 ,7 ,6 ,'Ensemble ces deux medicament sont dangereux');
insert into intercation values(4 ,9 ,3 ,'Ensemble ces deux medicament provoque une inflammation ');
insert into intercation values(5 ,7 ,1 ,'Ensemble ces deux medicament peuvent etre dangereux');
insert into intercation values(6 ,4 ,2 ,'Ensemble ces deus medicament provoque des effet negatifs des poumons');

-------------------------------------------------------------------------------remplissage table substance active
insert into substance_active values(1, 'amoxicilline trihydratée',
										table_medic(
											medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
											medic(2 ,'OMEPRAZOLE ABBOTT 10 mg, gélule gastro-résistante'),
											medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé')
												)
									);
insert into substance_active values(2, 'clavulanate de potassium',
										table_medic(
											medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
											medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé'),
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable')
												)
									);
insert into substance_active values(3, 'Oméprazole',
										table_medic(
										medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg')
												)
									);
insert into substance_active values(4, 'Clarithromycine',
										table_medic(
											medic(2 ,'OMEPRAZOLE ABBOTT 10 mg, gélule gastro-résistante'),
											medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé')
												)
									);									
insert into substance_active values(5, 'Spironolactone micronisée',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(8 ,'BARACLUDE 0,05 mg/ml')
												)
									);								
insert into substance_active values(6, 'Prednisone',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')
												)
									);
									
insert into substance_active values(7, 'Phosphate disodique de bétaméthasone',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(8 ,'BARACLUDE 0,05 mg/ml')
												)
									);								
insert into substance_active values(8, 'produit sur cellules diploïdes humaines (MRC-5)',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(7 ,'HAVRIX 1440'),
											medic(8 ,'BARACLUDE 0,05 mg/ml')
												)
									);									
insert into substance_active values(9, 'Entecavir',
										table_medic(
											medic(7 ,'HAVRIX 1440'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')
												)
									);									
insert into substance_active values(10, 'sofosbuvir',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(7 ,'HAVRIX 1440')
												)
									);
									
-------------------------------------------------------------------------------remplissage table effect indesirable
insert into effect_indesirable values(1 ,'nausées, vomissements, Allergies',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(7 ,'HAVRIX 1440')
												)
									);
insert into effect_indesirable values(2 ,'Apparition soudaine d’une respiration sifflante, Maux de tête',
										table_medic(
											medic(7 ,'HAVRIX 1440'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')
												)
									);
insert into effect_indesirable values(3 ,'allongement de lintervalle QT,  dépression, vertiges, trouble du goût, perte de lodorat',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(7 ,'HAVRIX 1440'),
											medic(8 ,'BARACLUDE 0,05 mg/ml')
												)
									);
insert into effect_indesirable values(4 ,'Troubles digestifs, Eruption cutanée, Insuffisance rénale aiguë',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(8 ,'BARACLUDE 0,05 mg/ml')
												)
									);
insert into effect_indesirable values(5 ,' modification de certains paramètres biologiques,  apparition de bleus, fragilité osseuse: ostéoporose, fracture',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')
												)
									);
insert into effect_indesirable values(6 ,'gonflement et rougeur du visage, prise de poids',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(8 ,'BARACLUDE 0,05 mg/ml')
												)
									);
insert into effect_indesirable values(7 ,'Irritabilité, Maux de tête, Douleur et rougeur au point d’injection',
										table_medic(
											medic(2 ,'OMEPRAZOLE ABBOTT 10 mg, gélule gastro-résistante'),
											medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé')
												)
									);
insert into effect_indesirable values(8 ,'Syndrome hépato-rénal',
										table_medic(
											medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
											medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé'),
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable')
												)
									);
									
-------------------------------------------------------------------------------remplissage table contre indication  
insert into contre_indic values(1 ,'Ne pas prendre si vous avez: allergie aux antibiotiques de la famille des bêta-lactamines',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(7 ,'HAVRIX 1440')
												)
									);
insert into contre_indic values(2 ,'Ne pas prendre si vous avez: antécédent d atteinte hépatique liée à l association amoxicilline/acide clavulanique',
										table_medic(
											medic(7 ,'HAVRIX 1440'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')
												)
									);
insert into contre_indic values(3 ,'Ne pas prendre si vous avez: vous êtes allergique (hypersensible)',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(7 ,'HAVRIX 1440'),
											medic(8 ,'BARACLUDE 0,05 mg/ml')
												)
									);
insert into contre_indic values(4 ,'Provoque des allergies aux antibiotiques de la famille des macrolides',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(8 ,'BARACLUDE 0,05 mg/ml')
												)
									);
insert into contre_indic values(5 ,'Ne pas prendre si vous avez:  insuffisance rénale, hyperkaliémie',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')
												)
									);
insert into contre_indic values(6 ,'Ne pas prendre si vous avez certaines maladies virales en évolution (hépatites virales, herpès, varicelle, zona)',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(8 ,'BARACLUDE 0,05 mg/ml')
												)
									);
insert into contre_indic values(7 ,'Ne pas prendre si vous avez: une infection, certains troubles mentaux non traités',
										table_medic(
											medic(2 ,'OMEPRAZOLE ABBOTT 10 mg, gélule gastro-résistante'),
											medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé')
												)
									);
insert into contre_indic values(8 ,'Ne pas prendre si vous avez une allergie connue à l un des composants du vaccin,  Infections fébriles sévères',
										table_medic(
											medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
											medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé'),
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable')
												)
									);
insert into contre_indic values(9 ,'Ne pas prendre si vous avez: une Hypersensibilité à la substance active',
										table_medic(
											medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
											medic(2 ,'OMEPRAZOLE ABBOTT 10 mg, gélule gastro-résistante'),
											medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé')
												)
									);

-------------------------------------------------------------------------------remplissage table indication 
insert into indication values(1 ,'Ce médicament est indiqué dans le traitement des infections dues aux germes sensibles',
										table_medic(
											medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
											medic(2 ,'OMEPRAZOLE ABBOTT 10 mg, gélule gastro-résistante'),
											medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé')
												)
									);
insert into indication values(2 ,'Pour reflux gastro-oeusophagien, et ulcères de la partie haute de votre intestin',
										table_medic(
											medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
											medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé'),
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable')
												)
									);
insert into indication values(3 ,'Ce médicament est indiqué chez l adulte dans le traitement de certaines infections bactériennes à germes sensibles',
										table_medic(
											medic(2 ,'OMEPRAZOLE ABBOTT 10 mg, gélule gastro-résistante'),
											medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé')
												)
									);
insert into indication values(4 ,'Ce médicament est indiqué dans le traitement de l hypertension artérielle, le traitement des oeudèmes',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(8 ,'BARACLUDE 0,05 mg/ml')
												)
									);
insert into indication values(5 ,'Il est indiqué dans certaines maladies, où il est utilisé pour son effet anti-inflammatoire',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')
												)
									);
insert into indication values(6 ,'Il peut être utilisé en injection locale, en dermatologie, en ophtalmologie, en ORL et en rhumatologie',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(8 ,'BARACLUDE 0,05 mg/ml')
												)
									);
insert into indication values(7 ,'Ce médicament est préconisé dans la prévention de l’infection provoquée par le virus de l’hépatite A',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(7 ,'HAVRIX 1440'),
											medic(8 ,'BARACLUDE 0,05 mg/ml')
												)
									);
insert into indication values(8 ,'Baraclude est indiqué dans le traitement des patients adultes atteints d une infection chronique par levirus de l hépatite B (VHB)',
										table_medic(
											medic(7 ,'HAVRIX 1440'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')
												)
									);
insert into indication values(9 ,'Sovaldi est indiqué, en association avec d’autres médicaments, pour le traitement de l’hépatite C ',
										table_medic(
											medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
											medic(5 ,'CORTANCYL 20 mg, comprimé sécable'),
											medic(7 ,'HAVRIX 1440')
												)
									);
									
-------------------------------------------------------------------------------remplissage table observation
insert into observation values( 1, 'fievre');
insert into observation values( 2, 'migraine');
insert into observation values( 3, 'maux de tete');
insert into observation values( 4, 'hémorragie interne');
insert into observation values( 5, 'migraine');
insert into observation values( 6, 'la tension');
insert into observation values( 7, 'la tension');
insert into observation values( 8, 'le diabete');
insert into observation values( 9, 'cholesterol');
insert into observation values( 10, 'fievre');
insert into observation values( 11, 'cholesterol');

-------------------------------------------------------------------------------remplissage table consultation
insert into consultation values(1,'12-01-2016','10h15',1,1);
insert into consultation values(2,'04-03-2016','12h15',2,2);	
insert into consultation values(3,'12-06-2016','08h00',3,3);	
insert into consultation values(4,'08-07-2016','09h00',4,4);	
insert into consultation values(5,'05-10-2016','10h00',5,5);	
insert into consultation values(6,'25-11-2019','14h00',6,6);	
insert into consultation values(7,'03-12-2016','15h00',7,7);	
insert into consultation values(8,'08-12-2016','11h20',8,8);	
insert into consultation values(9,'23-12-2016','08h30',9,9);	
insert into consultation values(10,'25-12-2016','14h30',10,10);	
insert into consultation values(11,'26-12-2016','11h15',11,11);

-------------------------------------------------------------------------------remplissage table substance-effect
insert into sub_effect values(1,1,2);
insert into sub_effect values(2,2,3);
insert into sub_effect values(3,3,8);
insert into sub_effect values(4,4,7);
insert into sub_effect values(5,5,6);
insert into sub_effect values(6,6,5);
insert into sub_effect values(7,7,4);
insert into sub_effect values(8,8,3);
insert into sub_effect values(9,9,2);
insert into sub_effect values(10,10,1);

-------------------------------------------------------------------------------remplissage table traitement
--traitement patient 1						
insert into traitement values (1,'doliprane 3 fois par jour....',4, '01-01-2016',1,1,111, 
									table_medic(
										medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
										medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
										medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')
											)
							);
insert into traitement values (2,'AMOXICILLINE une le matin une le soir....',4, '01-03-2016',2,1,121, 
									table_medic(
										medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
										medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
										medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')
											)
							);
insert into traitement values (3,'AMOXICILLINE une le matin une le soir....',30, '23-03-2016',3,1,1221, 
									table_medic(
										medic(7 ,'HAVRIX 1440'),
										medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
										medic(8 ,'BARACLUDE 0,05 mg/ml')
											)
							);
--traitement patient 2
insert into traitement values (4,'CORTANCYL 3 fois par jour....',7, '11-05-2016',4,2,122, 
									table_medic(
										medic(8 ,'BARACLUDE 0,05 mg/ml'),
										medic(5 ,'CORTANCYL 20 mg, comprimé sécable')
											)
							);
insert into traitement values (5,'doliprane 3 fois par jour....',8, '01-04-2016',5,2,132, 
									table_medic(
										medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
										medic(9 ,'SOVALDI 400 mg, comprimé pelliculé'),
										medic(4 ,'ALDACTONE 50 mg, comprimé sécable'),
										medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable')
											)
							);
--traitement patient 3
insert into traitement values (6,'havrix une fois par jour de preference le matin',4, '01-01-2016',8,3,123, 
									table_medic(
										medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
										medic(2 ,'OMEPRAZOLE ABBOTT 10 mg, gélule gastro-résistante'),
										medic(7 ,'HAVRIX 1440')
											)
							);
--traitement patient 3
insert into traitement values (7,'CLARITHROMYCINE 3 fois par jour....',4, '01-01-2016',9,3,131, 
									table_medic(
										medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
										medic(9 ,'SOVALDI 400 mg, comprimé pelliculé'),
										medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé')
											)
							);
insert into traitement values (8,'SOVALDI MIDI....',9, '01-02-2016',7,3,1222, 
									table_medic(
										medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
										medic(9 ,'SOVALDI 400 mg, comprimé pelliculé')
											)
							);	
insert into traitement values (9,'HAVRIX 1440 la nuit....',4, '01-03-2016',11,3,12231, 
									table_medic(
										medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
										medic(7 ,'HAVRIX 1440')
											)
							);
--traitement patient 4	
insert into traitement values (10,'CELESTENE deux fois par jour matin et soir',30, '14-10-2016',10,4,112, 
									table_medic(
										medic(6 ,'CELESTENE 4 mg/1 ml, solution injectable'),
										medic(3 ,'CLARITHROMYCINE ABBOTT 250 mg, comprimé pelliculé')
											)
							);	
insert into traitement values (11,'BARACLUDE 3 fois par jour....',10, '01-09-2016',6,4,12233, 
									table_medic(
										medic(1 ,'AMOXICILLINE / ACIDE CLAVULANIQUE MYLAN 500 mg'),
										medic(9 ,'SOVALDI 400 mg, comprimé pelliculé'),
										medic(8 ,'BARACLUDE 0,05 mg/ml'),
										medic(7 ,'HAVRIX 1440')
											)
							);
