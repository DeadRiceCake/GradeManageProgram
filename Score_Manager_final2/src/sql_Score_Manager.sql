drop table score_table CASCADE CONSTRAINTS;
drop table student_table CASCADE CONSTRAINTS;
drop table teacher_table CASCADE CONSTRAINTS;


/* ������ ���̺�*/
create table teacher_table
(TCH_NO number(10) NOT NULL,
USER_ID varchar2(20),
USER_PW varchar2(20),
NAME varchar2(20),
CLASS_NO number(10),
constraint TEACHER_TABLE_TCH_NO_PK primary key (TCH_NO)
);

/*�л� ���̺�*/
create table student_table
(STU_NO number(10) NOT NULL,
USER_ID varchar2(20),
USER_PW varchar2(20),
NAME varchar2(10),
TCH_NO number(10) NOT NULL,
CLASS_NO number(10),
constraint STUDENT_TABLE_STU_NO_PK primary key (STU_NO),
constraint STUDENT_TABLE_TCH_NO_FK foreign key (TCH_NO)
references TEACHER_TABLE (TCH_NO)
); 

/*���� ���̺�*/
create table score_table
(STU_NO number(10) NOT NULL,
TEST_DAY varchar2(20),
KOR number(10),
ENG number(10),
MATH number(10),
SOCIETY number(10),
SCIENCE number(10),
constraint SCORE_TABLE_STU_NO_FK foreign key (STU_NO)
references STUDENT_TABLE (STU_NO),
constraint SCORE_TABLE_TEST_DAY_CK check (test_day in('1-m', '1-e', '2-m', '2-e'))
);
/*�������� ���̺�*/
create table notice_table
(NOTICE_NO number(10),
NOTICE varchar2(4000),
constraint NOTICE_TABLE_NOTICE_NO_PK primary key (NOTICE_NO)
);

drop table notice_table

/*������ �α��� ���� �ӽ����� ���̺�*/
create table teacher_login_table
(TCH_NO number(10),
USER_ID varchar2(20),
USER_PW varchar2(20),
NAME varchar2(20),
CLASS_NO number(10)
);


create sequence notice_no
   increment by 1
   start with 1
   maxvalue 1000
   nocache
   nocycle;
   
   drop sequence stu_no;
   drop sequence tch_no;
   drop sequence notice_no;
   
   create sequence stu_no
   increment by 1
   start with 210
   maxvalue 1000
   nocache
   nocycle;
   
create sequence tch_no
   increment by 1
   start with 105
   maxvalue 1000
   nocache
   nocycle;
   
/*�뺻�� ������ �Է� : ������*/
insert into teacher_table
values (101, '�Ӹ���', '1111', '�Ӹ���', 1);
insert into teacher_table
values (102, 'ä����', '2222', 'ä����', 2);
insert into teacher_table
values (103, '����', '3333', '����', 3);
insert into teacher_table
values (104, '������', '4444', '������', 4);


/*�뺻�� ������ �Է� : �л�*/
insert into student_table
values (201, '������', '1111', '������', 101, 1);
insert into student_table
values (202, '���α�', '2222', '���α�', 101, 1);
insert into student_table
values (203, 'ä��ȭ', '3333', 'ä��ȭ', 101, 2);
insert into student_table
values (204, '�㼺��', '4444', '�㼺��', 101, 1);
insert into student_table
values (205, '���ֿ�', '5555', '���ֿ�', 101, 1);
insert into student_table
values (206, '�ֺ���', '6666', '�ֺ���', 101, 1);
insert into student_table
values (207, '�̰ǿ�', '7777', '�̰ǿ�', 101, 1);
insert into student_table
values (208, '����ö', '8888', '����ö', 101, 1);
insert into student_table
values (209, '������', '9999', '������', 101, 1);



/*�뺻�� ������ �Է� : ����*/
/*[1]*/
insert into score_table
values (201, '1-m', 80, 56, 54, 44, 50);
insert into score_table
values (201, '1-e', 70, 90, 45, 69, 78);
insert into score_table
values (201, '2-m', 55, 66, 77, 88, 99);
insert into score_table
values (201, '2-e', 45, 58, 35, 21, 94);
/*[2]*/
insert into score_table
values (202, '1-m',45 ,13 ,48 ,40 ,99);
insert into score_table
values (202, '1-e',50 , 75, 50, 24, 26);
insert into score_table
values (202, '2-m',41 ,38 ,37 ,98 ,65 );
insert into score_table
values (202, '2-e',76 ,23 ,93 ,55 ,13 );
/*[3]*/
insert into score_table
values (203, '1-m',29 ,71 ,69 ,73 ,32 );
insert into score_table
values (203, '1-e',47 ,86 ,49 ,46 ,85 );
insert into score_table
values (203, '2-m',15 ,12 ,78 ,95 ,75 );
insert into score_table
values (203, '2-e',58 ,30 ,37 ,58 ,75 );
/*[4]*/
insert into score_table
values (204, '1-m',78 ,96 ,40 ,18 , 33);
insert into score_table
values (204, '1-e',14 ,63 ,75 ,35 , 37);
insert into score_table
values (204, '2-m',78 ,58, 94, 49, 54);
insert into score_table
values (204, '2-e',83 , 53, 23,15 ,86 );
/*[5]*/
insert into score_table
values (205, '1-m',76 ,54 ,89 , 97,64 );
insert into score_table
values (205, '1-e', 53,69 ,54 ,86 ,35 );
insert into score_table
values (205, '2-m', 37,86, 43, 78, 75);
insert into score_table
values (205, '2-e',32 , 74, 56,65 ,73 );
/*[6]*/
insert into score_table
values (206, '1-m',31 ,53 ,63 ,98 ,91 );
insert into score_table
values (206, '1-e',85 ,84 ,68 , 65, 62);
insert into score_table
values (206, '2-m',80 ,40 ,65 ,78 ,56 );
insert into score_table
values (206, '2-e',65 ,36 ,77 ,45 , 58);
/*[7]*/
insert into score_table
values (207, '1-m',34 ,21 ,87 ,54 ,89 );
insert into score_table
values (207, '1-e',65 ,37 ,58 ,74 ,44 );
insert into score_table
values (207, '2-m',76 ,64 ,21 ,86 ,91 );
insert into score_table
values (207, '2-e',43 ,83 ,98 ,56 ,34 );
/*[8]*/
insert into score_table
values (208, '1-m',97 ,52 ,54 ,43 ,21 );
insert into score_table
values (208, '1-e',35 ,76 ,86 ,56 ,16 );
insert into score_table
values (208, '2-m',65 ,47 ,78 ,65 ,31 );
insert into score_table
values (208, '2-e',37 ,87 ,84 ,64 ,25 );
/*[9]*/
insert into score_table
values (209, '1-m',64 ,53 ,37 ,87 ,97 );
insert into score_table
values (209, '1-e',43 , 83,86 ,45 ,75);
insert into score_table
values (209, '2-m', 75, 64,23 ,52 ,35 );
insert into score_table
values (209, '2-e',67 ,54 ,45 ,35 ,17 );
   
insert into score_table
values (301, null, null, null, null, null, null);

insert into chart_table (select sc.stu_no, sc.test_day, sc.kor, sc.eng, sc.math, sc.society, sc.science from score_table sc, student_table st where sc.stu_no = st.stu_no and user_id = 'ff');

select kor from chart_table where test_day = '1-m';

create table teacher_login_table (TCH_NO number(10), USER_ID varchar2(20), USER_PW varchar2(20), NAME varchar2(20), CLASS_NO number(10));

select max(notice_no) max, notice from notice_table group by notice_no, notice
SELECT notice FROM notice_table WHERE notice_no = (SELECT max(notice_no) FROM notice_table);

select * from teacher_table;
select * from student_table;
select * from score_table
select * from notice_table
select * from chart_table;
select * from teacher_login_table;
delete from student_table where USER_ID = 'ss' and USER_PW = '1111'