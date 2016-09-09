create table Zip (
  zip int not null,
  country varchar(50) not null,
  state varchar(50) not null,
  city varchar(50) not null,
  primary key (zip)
);

create table Publisher (
  id int not null auto_increment,
  name varchar(50) not null,
  address varchar(100) not null,
  zip int not null,
  primary key (id),
  foreign key(zip) references Zip(zip)
  on delete cascade
);

create table Genre(
	id int not null auto_increment,
    name varchar(50) not null,
    primary key(id)
);

create table Author (
  id int not null auto_increment,
  name varchar(50) not null,
  surname varchar(50) not null,
  photo varchar(100) default null,
  primary key (id)
);

create table Book (
  id int not null auto_increment,
  title varchar(100) not null,
  description text not null,
  image varchar(100) default null,
  pages int not null,
  publisher_id int not null,
  publish_year int not null,
  primary key (id),
  foreign key(publisher_id) references Publisher(id)
  on delete cascade
);

create table Book_Author(
	book_id int not null,
    author_id int not null,
    primary key(book_id, author_id),
    foreign key(book_id) references Book(id)
    on delete cascade,
	foreign key(author_id) references Author(id)
    on delete cascade
);

create table Book_Genre(
	book_id int not null,
    genre_id int not null,
    primary key(book_id, genre_id),
    foreign key(book_id) references Book(id)
    on delete cascade,
	foreign key(genre_id) references Genre(id)
    on delete cascade
);