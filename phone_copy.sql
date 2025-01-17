PGDMP         0                {            phone    12.17    12.17 d    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16405    phone    DATABASE     �   CREATE DATABASE phone WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE phone;
                postgres    false            �            1255    16575    countratehum3(numeric)    FUNCTION       CREATE FUNCTION public.countratehum3(id_human numeric) RETURNS real
    LANGUAGE sql
    AS $$
    SELECT coalesce(sum(Rate.RateValue), 0)
	FROM JobTitle left join Rate on Rate.PK_JobTitle = JobTitle.PK_JobTitle
	and rate.dateofdismissal ISNULL
	where Rate.PK_Human = id_human;
$$;
 6   DROP FUNCTION public.countratehum3(id_human numeric);
       public          postgres    false            �            1255    16576 !   dismissal_human(numeric, integer) 	   PROCEDURE        CREATE PROCEDURE public.dismissal_human(id_human numeric, INOUT result integer DEFAULT 1)
    LANGUAGE sql
    AS $$
	UPDATE Rate 
	SET dateofdismissal = now()
	Where rate.pk_human = id_human AND
	rate.dateofdismissal ISNULL
	and rate.dateofemployment IS NOT NULL
	RETURNING result
;
$$;
 O   DROP PROCEDURE public.dismissal_human(id_human numeric, INOUT result integer);
       public          postgres    false            �            1255    16406    process_contact_audit()    FUNCTION     _  CREATE FUNCTION public.process_contact_audit() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        --
        -- Добавление строки в AuditContact, которая отражает операцию, выполняемую в Contact;
        -- для определения типа операции применяется специальная переменная TG_OP.
        --
        IF (TG_OP = 'DELETE') THEN
            INSERT INTO AuditContact SELECT nextval('example_table_id_seq'), user, now(), 'D', OLD.DateOfConnect, OLD.ValueContact, OLD.PK_Contact;
        ELSIF (TG_OP = 'UPDATE') THEN
            INSERT INTO AuditContact SELECT nextval('example_table_id_seq'), user, now(), 'U', NEW.DateOfConnect, NEW.ValueContact, NEW.PK_Contact;
        ELSIF (TG_OP = 'INSERT') THEN
            INSERT INTO AuditContact SELECT nextval('example_table_id_seq'), user, now(), 'I', NEW.DateOfConnect, NEW.ValueContact, NEW.PK_Contact;
        END IF;
        RETURN NULL; -- возвращаемое значение для триггера AFTER игнорируется
    END;
$$;
 .   DROP FUNCTION public.process_contact_audit();
       public          postgres    false            �            1259    16407    auditcontact    TABLE       CREATE TABLE public.auditcontact (
    pk_auditcontact integer NOT NULL,
    userone character varying(100),
    dateedit date,
    typereqest character varying(100),
    dateofconnect date,
    valuecontact character varying(150),
    pk_contact integer NOT NULL
);
     DROP TABLE public.auditcontact;
       public         heap    postgres    false            �            1259    16410     auditcontact_pk_auditcontact_seq    SEQUENCE     �   CREATE SEQUENCE public.auditcontact_pk_auditcontact_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.auditcontact_pk_auditcontact_seq;
       public          postgres    false    202            �           0    0     auditcontact_pk_auditcontact_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.auditcontact_pk_auditcontact_seq OWNED BY public.auditcontact.pk_auditcontact;
          public          postgres    false    203            �            1259    16412    branch    TABLE     �   CREATE TABLE public.branch (
    pk_branch integer NOT NULL,
    address character varying(500),
    pk_organization integer NOT NULL
);
    DROP TABLE public.branch;
       public         heap    postgres    false            �            1259    16418    branch_pk_branch_seq    SEQUENCE     �   CREATE SEQUENCE public.branch_pk_branch_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.branch_pk_branch_seq;
       public          postgres    false    204            �           0    0    branch_pk_branch_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.branch_pk_branch_seq OWNED BY public.branch.pk_branch;
          public          postgres    false    205            �            1259    16420    contact    TABLE     �   CREATE TABLE public.contact (
    pk_contact integer NOT NULL,
    valuecontact character varying(150) NOT NULL,
    dateofconnect date,
    pk_human integer NOT NULL,
    pk_stateofcontact integer NOT NULL,
    pk_typeofcontact integer NOT NULL
);
    DROP TABLE public.contact;
       public         heap    postgres    false            �            1259    16423    contact_pk_contact_seq    SEQUENCE     �   CREATE SEQUENCE public.contact_pk_contact_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.contact_pk_contact_seq;
       public          postgres    false    206            �           0    0    contact_pk_contact_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.contact_pk_contact_seq OWNED BY public.contact.pk_contact;
          public          postgres    false    207            �            1259    16425    example_table_id_seq    SEQUENCE     }   CREATE SEQUENCE public.example_table_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.example_table_id_seq;
       public          postgres    false            �            1259    16427    human    TABLE     �   CREATE TABLE public.human (
    pk_human integer NOT NULL,
    namehuman character varying(150) NOT NULL,
    surname character varying(150) NOT NULL,
    patronymic character varying(150),
    dateofbirth date,
    email character varying(100)
);
    DROP TABLE public.human;
       public         heap    postgres    false            �            1259    16433    human_pk_human_seq    SEQUENCE     �   CREATE SEQUENCE public.human_pk_human_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.human_pk_human_seq;
       public          postgres    false    209            �           0    0    human_pk_human_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.human_pk_human_seq OWNED BY public.human.pk_human;
          public          postgres    false    210            �            1259    16435    jobtitle    TABLE     w   CREATE TABLE public.jobtitle (
    pk_jobtitle integer NOT NULL,
    nameofjobtitle character varying(250) NOT NULL
);
    DROP TABLE public.jobtitle;
       public         heap    postgres    false            �            1259    16438    jobtitle_pk_jobtitle_seq    SEQUENCE     �   CREATE SEQUENCE public.jobtitle_pk_jobtitle_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.jobtitle_pk_jobtitle_seq;
       public          postgres    false    211            �           0    0    jobtitle_pk_jobtitle_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.jobtitle_pk_jobtitle_seq OWNED BY public.jobtitle.pk_jobtitle;
          public          postgres    false    212            �            1259    16440    office    TABLE     �   CREATE TABLE public.office (
    pk_office integer NOT NULL,
    numberoffice integer NOT NULL,
    pk_subdivision integer NOT NULL,
    pk_human integer NOT NULL
);
    DROP TABLE public.office;
       public         heap    postgres    false            �            1259    16443    office_pk_office_seq    SEQUENCE     �   CREATE SEQUENCE public.office_pk_office_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.office_pk_office_seq;
       public          postgres    false    213            �           0    0    office_pk_office_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.office_pk_office_seq OWNED BY public.office.pk_office;
          public          postgres    false    214            �            1259    16445    organization    TABLE     �   CREATE TABLE public.organization (
    pk_organization integer NOT NULL,
    nameorganization character varying(700) NOT NULL,
    address character varying(500),
    email character varying(100)
);
     DROP TABLE public.organization;
       public         heap    postgres    false            �            1259    16451     organization_pk_organization_seq    SEQUENCE     �   CREATE SEQUENCE public.organization_pk_organization_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.organization_pk_organization_seq;
       public          postgres    false    215            �           0    0     organization_pk_organization_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.organization_pk_organization_seq OWNED BY public.organization.pk_organization;
          public          postgres    false    216            �            1259    16453    rate    TABLE     �   CREATE TABLE public.rate (
    pk_rate integer NOT NULL,
    ratevalue real NOT NULL,
    dateofemployment date NOT NULL,
    dateofdismissal date,
    pk_subdivision integer NOT NULL,
    pk_jobtitle integer NOT NULL,
    pk_human integer NOT NULL
);
    DROP TABLE public.rate;
       public         heap    postgres    false            �            1259    16456    rate_pk_rate_seq    SEQUENCE     �   CREATE SEQUENCE public.rate_pk_rate_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.rate_pk_rate_seq;
       public          postgres    false    217            �           0    0    rate_pk_rate_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.rate_pk_rate_seq OWNED BY public.rate.pk_rate;
          public          postgres    false    218            �            1259    16458    stateofcontact    TABLE     �   CREATE TABLE public.stateofcontact (
    pk_stateofcontact integer NOT NULL,
    valuecontactstate character varying(100) NOT NULL
);
 "   DROP TABLE public.stateofcontact;
       public         heap    postgres    false            �            1259    16461 $   stateofcontact_pk_stateofcontact_seq    SEQUENCE     �   CREATE SEQUENCE public.stateofcontact_pk_stateofcontact_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.stateofcontact_pk_stateofcontact_seq;
       public          postgres    false    219            �           0    0 $   stateofcontact_pk_stateofcontact_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE public.stateofcontact_pk_stateofcontact_seq OWNED BY public.stateofcontact.pk_stateofcontact;
          public          postgres    false    220            �            1259    16463    subdivision    TABLE     Q  CREATE TABLE public.subdivision (
    pk_subdivision integer NOT NULL,
    namesubdivision character varying(400) NOT NULL,
    kodsubdivision character varying(100),
    abbreviation character varying(100),
    email character varying(100),
    pk_branch integer,
    pk_organization integer NOT NULL,
    pk_subdivision_two integer
);
    DROP TABLE public.subdivision;
       public         heap    postgres    false            �            1259    16469    subdivision_pk_subdivision_seq    SEQUENCE     �   CREATE SEQUENCE public.subdivision_pk_subdivision_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.subdivision_pk_subdivision_seq;
       public          postgres    false    221            �           0    0    subdivision_pk_subdivision_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.subdivision_pk_subdivision_seq OWNED BY public.subdivision.pk_subdivision;
          public          postgres    false    222            �            1259    16471    typeofcontact    TABLE     �   CREATE TABLE public.typeofcontact (
    pk_typeofcontact integer NOT NULL,
    valuetupecontact character varying(150) NOT NULL
);
 !   DROP TABLE public.typeofcontact;
       public         heap    postgres    false            �            1259    16474 "   typeofcontact_pk_typeofcontact_seq    SEQUENCE     �   CREATE SEQUENCE public.typeofcontact_pk_typeofcontact_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.typeofcontact_pk_typeofcontact_seq;
       public          postgres    false    223            �           0    0 "   typeofcontact_pk_typeofcontact_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.typeofcontact_pk_typeofcontact_seq OWNED BY public.typeofcontact.pk_typeofcontact;
          public          postgres    false    224            �
           2604    16476    auditcontact pk_auditcontact    DEFAULT     �   ALTER TABLE ONLY public.auditcontact ALTER COLUMN pk_auditcontact SET DEFAULT nextval('public.auditcontact_pk_auditcontact_seq'::regclass);
 K   ALTER TABLE public.auditcontact ALTER COLUMN pk_auditcontact DROP DEFAULT;
       public          postgres    false    203    202            �
           2604    16477    branch pk_branch    DEFAULT     t   ALTER TABLE ONLY public.branch ALTER COLUMN pk_branch SET DEFAULT nextval('public.branch_pk_branch_seq'::regclass);
 ?   ALTER TABLE public.branch ALTER COLUMN pk_branch DROP DEFAULT;
       public          postgres    false    205    204            �
           2604    16478    contact pk_contact    DEFAULT     x   ALTER TABLE ONLY public.contact ALTER COLUMN pk_contact SET DEFAULT nextval('public.contact_pk_contact_seq'::regclass);
 A   ALTER TABLE public.contact ALTER COLUMN pk_contact DROP DEFAULT;
       public          postgres    false    207    206            �
           2604    16479    human pk_human    DEFAULT     p   ALTER TABLE ONLY public.human ALTER COLUMN pk_human SET DEFAULT nextval('public.human_pk_human_seq'::regclass);
 =   ALTER TABLE public.human ALTER COLUMN pk_human DROP DEFAULT;
       public          postgres    false    210    209            �
           2604    16480    jobtitle pk_jobtitle    DEFAULT     |   ALTER TABLE ONLY public.jobtitle ALTER COLUMN pk_jobtitle SET DEFAULT nextval('public.jobtitle_pk_jobtitle_seq'::regclass);
 C   ALTER TABLE public.jobtitle ALTER COLUMN pk_jobtitle DROP DEFAULT;
       public          postgres    false    212    211            �
           2604    16481    office pk_office    DEFAULT     t   ALTER TABLE ONLY public.office ALTER COLUMN pk_office SET DEFAULT nextval('public.office_pk_office_seq'::regclass);
 ?   ALTER TABLE public.office ALTER COLUMN pk_office DROP DEFAULT;
       public          postgres    false    214    213            �
           2604    16482    organization pk_organization    DEFAULT     �   ALTER TABLE ONLY public.organization ALTER COLUMN pk_organization SET DEFAULT nextval('public.organization_pk_organization_seq'::regclass);
 K   ALTER TABLE public.organization ALTER COLUMN pk_organization DROP DEFAULT;
       public          postgres    false    216    215            �
           2604    16483    rate pk_rate    DEFAULT     l   ALTER TABLE ONLY public.rate ALTER COLUMN pk_rate SET DEFAULT nextval('public.rate_pk_rate_seq'::regclass);
 ;   ALTER TABLE public.rate ALTER COLUMN pk_rate DROP DEFAULT;
       public          postgres    false    218    217            �
           2604    16484     stateofcontact pk_stateofcontact    DEFAULT     �   ALTER TABLE ONLY public.stateofcontact ALTER COLUMN pk_stateofcontact SET DEFAULT nextval('public.stateofcontact_pk_stateofcontact_seq'::regclass);
 O   ALTER TABLE public.stateofcontact ALTER COLUMN pk_stateofcontact DROP DEFAULT;
       public          postgres    false    220    219            �
           2604    16485    subdivision pk_subdivision    DEFAULT     �   ALTER TABLE ONLY public.subdivision ALTER COLUMN pk_subdivision SET DEFAULT nextval('public.subdivision_pk_subdivision_seq'::regclass);
 I   ALTER TABLE public.subdivision ALTER COLUMN pk_subdivision DROP DEFAULT;
       public          postgres    false    222    221            �
           2604    16486    typeofcontact pk_typeofcontact    DEFAULT     �   ALTER TABLE ONLY public.typeofcontact ALTER COLUMN pk_typeofcontact SET DEFAULT nextval('public.typeofcontact_pk_typeofcontact_seq'::regclass);
 M   ALTER TABLE public.typeofcontact ALTER COLUMN pk_typeofcontact DROP DEFAULT;
       public          postgres    false    224    223            q          0    16407    auditcontact 
   TABLE DATA              COPY public.auditcontact (pk_auditcontact, userone, dateedit, typereqest, dateofconnect, valuecontact, pk_contact) FROM stdin;
    public          postgres    false    202   ��       s          0    16412    branch 
   TABLE DATA           E   COPY public.branch (pk_branch, address, pk_organization) FROM stdin;
    public          postgres    false    204   k�       u          0    16420    contact 
   TABLE DATA           y   COPY public.contact (pk_contact, valuecontact, dateofconnect, pk_human, pk_stateofcontact, pk_typeofcontact) FROM stdin;
    public          postgres    false    206   �       x          0    16427    human 
   TABLE DATA           ]   COPY public.human (pk_human, namehuman, surname, patronymic, dateofbirth, email) FROM stdin;
    public          postgres    false    209   ��       z          0    16435    jobtitle 
   TABLE DATA           ?   COPY public.jobtitle (pk_jobtitle, nameofjobtitle) FROM stdin;
    public          postgres    false    211          |          0    16440    office 
   TABLE DATA           S   COPY public.office (pk_office, numberoffice, pk_subdivision, pk_human) FROM stdin;
    public          postgres    false    213   @�       ~          0    16445    organization 
   TABLE DATA           Y   COPY public.organization (pk_organization, nameorganization, address, email) FROM stdin;
    public          postgres    false    215   ��       �          0    16453    rate 
   TABLE DATA           |   COPY public.rate (pk_rate, ratevalue, dateofemployment, dateofdismissal, pk_subdivision, pk_jobtitle, pk_human) FROM stdin;
    public          postgres    false    217   �       �          0    16458    stateofcontact 
   TABLE DATA           N   COPY public.stateofcontact (pk_stateofcontact, valuecontactstate) FROM stdin;
    public          postgres    false    219   ��       �          0    16463    subdivision 
   TABLE DATA           �   COPY public.subdivision (pk_subdivision, namesubdivision, kodsubdivision, abbreviation, email, pk_branch, pk_organization, pk_subdivision_two) FROM stdin;
    public          postgres    false    221   ֆ       �          0    16471    typeofcontact 
   TABLE DATA           K   COPY public.typeofcontact (pk_typeofcontact, valuetupecontact) FROM stdin;
    public          postgres    false    223   ��       �           0    0     auditcontact_pk_auditcontact_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.auditcontact_pk_auditcontact_seq', 1, true);
          public          postgres    false    203            �           0    0    branch_pk_branch_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.branch_pk_branch_seq', 6, true);
          public          postgres    false    205            �           0    0    contact_pk_contact_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.contact_pk_contact_seq', 12, true);
          public          postgres    false    207            �           0    0    example_table_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.example_table_id_seq', 10, true);
          public          postgres    false    208            �           0    0    human_pk_human_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.human_pk_human_seq', 7, true);
          public          postgres    false    210            �           0    0    jobtitle_pk_jobtitle_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.jobtitle_pk_jobtitle_seq', 7, true);
          public          postgres    false    212            �           0    0    office_pk_office_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.office_pk_office_seq', 7, true);
          public          postgres    false    214            �           0    0     organization_pk_organization_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.organization_pk_organization_seq', 9, true);
          public          postgres    false    216            �           0    0    rate_pk_rate_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.rate_pk_rate_seq', 8, true);
          public          postgres    false    218            �           0    0 $   stateofcontact_pk_stateofcontact_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('public.stateofcontact_pk_stateofcontact_seq', 4, true);
          public          postgres    false    220            �           0    0    subdivision_pk_subdivision_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.subdivision_pk_subdivision_seq', 11, true);
          public          postgres    false    222            �           0    0 "   typeofcontact_pk_typeofcontact_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('public.typeofcontact_pk_typeofcontact_seq', 5, true);
          public          postgres    false    224            �
           2606    16488    auditcontact auditcontact_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.auditcontact
    ADD CONSTRAINT auditcontact_pkey PRIMARY KEY (pk_auditcontact);
 H   ALTER TABLE ONLY public.auditcontact DROP CONSTRAINT auditcontact_pkey;
       public            postgres    false    202            �
           2606    16490    branch branch_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.branch
    ADD CONSTRAINT branch_pkey PRIMARY KEY (pk_branch);
 <   ALTER TABLE ONLY public.branch DROP CONSTRAINT branch_pkey;
       public            postgres    false    204            �
           2606    16492    contact contact_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.contact
    ADD CONSTRAINT contact_pkey PRIMARY KEY (pk_contact);
 >   ALTER TABLE ONLY public.contact DROP CONSTRAINT contact_pkey;
       public            postgres    false    206            �
           2606    16494    human human_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.human
    ADD CONSTRAINT human_pkey PRIMARY KEY (pk_human);
 :   ALTER TABLE ONLY public.human DROP CONSTRAINT human_pkey;
       public            postgres    false    209            �
           2606    16496    jobtitle jobtitle_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.jobtitle
    ADD CONSTRAINT jobtitle_pkey PRIMARY KEY (pk_jobtitle);
 @   ALTER TABLE ONLY public.jobtitle DROP CONSTRAINT jobtitle_pkey;
       public            postgres    false    211            �
           2606    16498    office office_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.office
    ADD CONSTRAINT office_pkey PRIMARY KEY (pk_office);
 <   ALTER TABLE ONLY public.office DROP CONSTRAINT office_pkey;
       public            postgres    false    213            �
           2606    16500    organization organization_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (pk_organization);
 H   ALTER TABLE ONLY public.organization DROP CONSTRAINT organization_pkey;
       public            postgres    false    215            �
           2606    16502    rate rate_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.rate
    ADD CONSTRAINT rate_pkey PRIMARY KEY (pk_rate);
 8   ALTER TABLE ONLY public.rate DROP CONSTRAINT rate_pkey;
       public            postgres    false    217            �
           2606    16504 "   stateofcontact stateofcontact_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.stateofcontact
    ADD CONSTRAINT stateofcontact_pkey PRIMARY KEY (pk_stateofcontact);
 L   ALTER TABLE ONLY public.stateofcontact DROP CONSTRAINT stateofcontact_pkey;
       public            postgres    false    219            �
           2606    16506    subdivision subdivision_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.subdivision
    ADD CONSTRAINT subdivision_pkey PRIMARY KEY (pk_subdivision);
 F   ALTER TABLE ONLY public.subdivision DROP CONSTRAINT subdivision_pkey;
       public            postgres    false    221            �
           2606    16508     typeofcontact typeofcontact_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.typeofcontact
    ADD CONSTRAINT typeofcontact_pkey PRIMARY KEY (pk_typeofcontact);
 J   ALTER TABLE ONLY public.typeofcontact DROP CONSTRAINT typeofcontact_pkey;
       public            postgres    false    223            �
           2620    16509    contact auditcontact    TRIGGER     �   CREATE TRIGGER auditcontact AFTER INSERT OR DELETE OR UPDATE ON public.contact FOR EACH ROW EXECUTE FUNCTION public.process_contact_audit();
 -   DROP TRIGGER auditcontact ON public.contact;
       public          postgres    false    206    225            �
           2606    16510 )   auditcontact auditcontact_pk_contact_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.auditcontact
    ADD CONSTRAINT auditcontact_pk_contact_fkey FOREIGN KEY (pk_contact) REFERENCES public.contact(pk_contact) ON UPDATE RESTRICT ON DELETE RESTRICT;
 S   ALTER TABLE ONLY public.auditcontact DROP CONSTRAINT auditcontact_pk_contact_fkey;
       public          postgres    false    206    2772    202            �
           2606    16515 "   branch branch_pk_organization_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.branch
    ADD CONSTRAINT branch_pk_organization_fkey FOREIGN KEY (pk_organization) REFERENCES public.organization(pk_organization) ON UPDATE RESTRICT ON DELETE RESTRICT;
 L   ALTER TABLE ONLY public.branch DROP CONSTRAINT branch_pk_organization_fkey;
       public          postgres    false    2780    204    215            �
           2606    16520    contact contact_pk_human_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.contact
    ADD CONSTRAINT contact_pk_human_fkey FOREIGN KEY (pk_human) REFERENCES public.human(pk_human) ON UPDATE RESTRICT ON DELETE RESTRICT;
 G   ALTER TABLE ONLY public.contact DROP CONSTRAINT contact_pk_human_fkey;
       public          postgres    false    209    2774    206            �
           2606    16525 &   contact contact_pk_stateofcontact_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.contact
    ADD CONSTRAINT contact_pk_stateofcontact_fkey FOREIGN KEY (pk_stateofcontact) REFERENCES public.stateofcontact(pk_stateofcontact) ON UPDATE RESTRICT ON DELETE RESTRICT;
 P   ALTER TABLE ONLY public.contact DROP CONSTRAINT contact_pk_stateofcontact_fkey;
       public          postgres    false    206    2784    219            �
           2606    16530 %   contact contact_pk_typeofcontact_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.contact
    ADD CONSTRAINT contact_pk_typeofcontact_fkey FOREIGN KEY (pk_typeofcontact) REFERENCES public.typeofcontact(pk_typeofcontact) ON UPDATE RESTRICT ON DELETE RESTRICT;
 O   ALTER TABLE ONLY public.contact DROP CONSTRAINT contact_pk_typeofcontact_fkey;
       public          postgres    false    206    2788    223            �
           2606    16535    office office_pk_human_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.office
    ADD CONSTRAINT office_pk_human_fkey FOREIGN KEY (pk_human) REFERENCES public.human(pk_human) ON UPDATE RESTRICT ON DELETE RESTRICT;
 E   ALTER TABLE ONLY public.office DROP CONSTRAINT office_pk_human_fkey;
       public          postgres    false    209    2774    213            �
           2606    16540 !   office office_pk_subdivision_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.office
    ADD CONSTRAINT office_pk_subdivision_fkey FOREIGN KEY (pk_subdivision) REFERENCES public.subdivision(pk_subdivision) ON UPDATE RESTRICT ON DELETE RESTRICT;
 K   ALTER TABLE ONLY public.office DROP CONSTRAINT office_pk_subdivision_fkey;
       public          postgres    false    221    2786    213            �
           2606    16545    rate rate_pk_human_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.rate
    ADD CONSTRAINT rate_pk_human_fkey FOREIGN KEY (pk_human) REFERENCES public.human(pk_human) ON UPDATE RESTRICT ON DELETE RESTRICT;
 A   ALTER TABLE ONLY public.rate DROP CONSTRAINT rate_pk_human_fkey;
       public          postgres    false    217    2774    209            �
           2606    16550    rate rate_pk_jobtitle_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.rate
    ADD CONSTRAINT rate_pk_jobtitle_fkey FOREIGN KEY (pk_jobtitle) REFERENCES public.jobtitle(pk_jobtitle) ON UPDATE RESTRICT ON DELETE RESTRICT;
 D   ALTER TABLE ONLY public.rate DROP CONSTRAINT rate_pk_jobtitle_fkey;
       public          postgres    false    2776    217    211            �
           2606    16555    rate rate_pk_subdivision_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.rate
    ADD CONSTRAINT rate_pk_subdivision_fkey FOREIGN KEY (pk_subdivision) REFERENCES public.subdivision(pk_subdivision) ON UPDATE RESTRICT ON DELETE RESTRICT;
 G   ALTER TABLE ONLY public.rate DROP CONSTRAINT rate_pk_subdivision_fkey;
       public          postgres    false    217    2786    221            �
           2606    16560 &   subdivision subdivision_pk_branch_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.subdivision
    ADD CONSTRAINT subdivision_pk_branch_fkey FOREIGN KEY (pk_branch) REFERENCES public.branch(pk_branch) ON UPDATE RESTRICT ON DELETE RESTRICT;
 P   ALTER TABLE ONLY public.subdivision DROP CONSTRAINT subdivision_pk_branch_fkey;
       public          postgres    false    204    221    2770            �
           2606    16565 ,   subdivision subdivision_pk_organization_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.subdivision
    ADD CONSTRAINT subdivision_pk_organization_fkey FOREIGN KEY (pk_organization) REFERENCES public.organization(pk_organization) ON UPDATE RESTRICT ON DELETE RESTRICT;
 V   ALTER TABLE ONLY public.subdivision DROP CONSTRAINT subdivision_pk_organization_fkey;
       public          postgres    false    221    2780    215            �
           2606    16570 /   subdivision subdivision_pk_subdivision_two_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.subdivision
    ADD CONSTRAINT subdivision_pk_subdivision_two_fkey FOREIGN KEY (pk_subdivision_two) REFERENCES public.subdivision(pk_subdivision) ON UPDATE RESTRICT ON DELETE RESTRICT;
 Y   ALTER TABLE ONLY public.subdivision DROP CONSTRAINT subdivision_pk_subdivision_two_fkey;
       public          postgres    false    2786    221    221            q   �   x����
1Dϓ_�J�&i�O
�ͫxT\����C��7aR��6<��ˀS�G�w��{��j`J���hb��Z���@�Ds��}��x�>���*Z�KQI���-�Y������J�/bZ��f���dɢ.p���q������wu�(s�]h�i�c��^ީg_      s   g   x�U��	�0E�)�����q�4֖
�!ĸ��F���k>�}C�j�
2n�BT�'�aEąXG$ٽ#ˎ����k@i0��Ǘ�IY/��ߡi�����:�      u   �   x�=�[
CAC�u+�2��{����8s)	F�\"%�li�R��l��yd�6_-+�b����̖��Snr��'w���ۑ;كƘ<GF6���OY�<���P���3��I�Ur���aUt��.�>v�z�~���Js+�      x   2  x�U��N�@�׷�ҦE�t�'�	�'�MZ aWA��Dј�1��T~"��7�L����霯߹�3��T��%~������%���%�s�vM���i4�_���:��F���wx�SsΉ�j����vZ�홎K��,�ӣl�����ӆs��"�]%�^e`���-�@��m�nbH��@��+�-⧺�;���� }������cu�GW��d6'��OE(+���!�F��Me��[�e~"}� l` 'S=�����.�Z R꨺ҞzaE�����pU����u��!��H�/��g���>�C      z   n   x��KDPE�U����f,C������yk��#�I����䄂+nn���	Z�V�}=���?���VX���b#���D���*̄'���oYM��,P�j=L|      |   4   x�-��	 0�޹a���e���P!��!ϸ7#Iկ���imbVQ:K����      ~   �   x�3�0��/츰��6p^��AO���[/�
ｰA�Ĝ3� )��!=713G/9?�˘����.l�8�b6-�F�I�IE`E�\&�&��[ӄCGY~nRb�Ceb^JjHW� �0T2      �   i   x�E���0C��.��,��?N���S?7��U<d{nk)t�:����3�)E+Q�3����]�0�����p��D����X�h_E/G	��		O�S�      �   9   x�3估�®�Mv\�ta녽\F��^ت�&jr��.6��^������ ��(�      �   �   x�]�M�0���SxB+������@�QZ�?��r�k<�1!���a��C1.�L2����/����-��� �E�˙�4Nbrz� �x&sݝh>��VeT!Bk/6���	� ���^2�=`E��f��G�����/J)G},V�&Wrk�N�]o�A��ve_B��[������A��� 2�l      �   P   x�3估�¾{.l�ؠp������������8/6^��wa���;�Tp�p��&f�p�r6\�u��+F��� 21�     