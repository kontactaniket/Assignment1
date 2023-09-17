create table RECORD(
  ID int not null,
  CREATED DATE not null,
  NAME varchar(100),
  PRICE float,
  GENRE varchar(100),
  constraint uq_reg unique (NAME)
);