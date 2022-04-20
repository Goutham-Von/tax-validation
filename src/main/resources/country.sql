USE txnvalidationDB;

DROP TABLE country;

CREATE TABLE taxvalidators (
                               taxvalidator varchar(50) PRIMARY KEY,
                               isworking char(3) DEFAULT 'YES'
);

CREATE TABLE validators (
                            countrycode char(2),
                            taxvalidator varchar(50),
                            FOREIGN KEY (taxvalidator) REFERENCES taxvalidators(taxvalidator)
);

CREATE TABLE country (
                         countryname varchar(50) PRIMARY KEY,
                         code char(2) DEFAULT NULL,
                         regex varchar(100) DEFAULT NULL
);



INSERT INTO taxvalidators(taxvalidator) VALUES
                                            ('TaxIDProValidator'),
                                            ('FonoaValidator');



INSERT INTO country(countryname, code)
VALUES
    ('Afghanistan','AF'),
    ('Åland','AX'),
    ('Albania','AL'),
    ('Algeria','DZ'),
    ('American Samoa','AS'),
    ('Andorra','AD'),
    ('Angola','AO'),
    ('Anguilla','AI'),
    ('Antarctica','AQ'),
    ('Antigua and Barbuda','AG'),
    ('Argentina','AR'),
    ('Armenia','AM'),
    ('Aruba','AW'),
    ('Australia','AU'),
    ('Austria','AT'),
    ('Azerbaijan','AZ'),
    ('Bahamas','BS'),
    ('Bahrain','BH'),
    ('Bangladesh','BD'),
    ('Barbados','BB'),
    ('Belarus','BY'),
    ('Belgium','BE'),
    ('Belize','BZ'),
    ('Benin','BJ'),
    ('Bermuda','BM'),
    ('Bhutan','BT'),
    ('Bolivia','BO'),
    ('Bonaire','BQ'),
    ('Bosnia and Herzegovina','BA'),
    ('Botswana','BW'),
    ('Bouvet Island','BV'),
    ('Brazil','BR'),
    ('British Indian Ocean Territory','IO'),
    ('British Virgin Islands','VG'),
    ('Brunei','BN'),
    ('Bulgaria','BG'),
    ('Burkina Faso','BF'),
    ('Burundi','BI'),
    ('Cambodia','KH'),
    ('Cameroon','CM'),
    ('Canada','CA'),
    ('Cape Verde','CV'),
    ('Cayman Islands','KY'),
    ('Central African Republic','CF'),
    ('Chad','TD'),
    ('Chile','CL'),
    ('China','CN'),
    ('Christmas Island','CX'),
    ('Cocos [Keeling] Islands','CC'),
    ('Colombia','CO'),
    ('Comoros','KM'),
    ('Cook Islands','CK'),
    ('Costa Rica','CR'),
    ('Croatia','HR'),
    ('Cuba','CU'),
    ('Curacao','CW'),
    ('Cyprus','CY'),
    ('Czech Republic','CZ'),
    ('Democratic Republic of the Congo','CD'),
    ('Denmark','DK'),
    ('Djibouti','DJ'),
    ('Dominica','DM'),
    ('Dominican Republic','DO'),
    ('East Timor','TL'),
    ('Ecuador','EC'),
    ('Egypt','EG'),
    ('El Salvador','SV'),
    ('Equatorial Guinea','GQ'),
    ('Eritrea','ER'),
    ('Estonia','EE'),
    ('Ethiopia','ET'),
    ('Falkland Islands','FK'),
    ('Faroe Islands','FO'),
    ('Fiji','FJ'),
    ('Finland','FI'),
    ('France','FR'),
    ('French Guiana','GF'),
    ('French Polynesia','PF'),
    ('French Southern Territories','TF'),
    ('Gabon','GA'),
    ('Gambia','GM'),
    ('Georgia','GE'),
    ('Germany','DE'),
    ('Ghana','GH'),
    ('Gibraltar','GI'),
    ('Greece','GR'),
    ('Greenland','GL'),
    ('Grenada','GD'),
    ('Guadeloupe','GP'),
    ('Guam','GU'),
    ('Guatemala','GT'),
    ('Guernsey','GG'),
    ('Haiti','HT'),
    ('Heard Island and McDonald Islands','HM'),
    ('Honduras','HN'),
    ('Hong Kong','HK'),
    ('Hungary','HU'),
    ('Iceland','IS'),
    ('India', 'IN'),
    ('Indonesia','ID'),
    ('Iran','IR'),
    ('Iraq','IQ'),
    ('Ireland','IE'),
    ('Isle of Man','IM'),
    ('Israel','IL'),
    ('Italy','IT'),
    ('Ivory Coast','CI'),
    ('Jamaica','JM'),
    ('Japan','JP'),
    ('Jersey','JE'),
    ('Jordan','JO'),
    ('Kazakhstan','KZ'),
    ('Kenya','KE'),
    ('Palau','PW'),
    ('Palestine','PS'),
    ('Panama','PA'),
    ('Papua New Guinea','PG'),
    ('Paraguay','PY'),
    ('Peru','PE'),
    ('Philippines','PH'),
    ('Pitcairn Islands','PN'),
    ('Poland','PL'),
    ('Portugal','PT'),
    ('Puerto Rico','PR'),
    ('Qatar','QA'),
    ('Republic of the Congo','CG'),
    ('Réunion','RE'),
    ('Romania','RO'),
    ('Sint Maarten','SX'),
    ('Slovakia','SK'),
    ('Slovenia','SI'),
    ('Solomon Islands','SB'),
    ('Somalia','SO'),
    ('South Africa','ZA'),
    ('South Georgia and the South Sandwich Islands','GS'),
    ('South Korea','KR'),
    ('South Sudan','SS'),
    ('Spain','ES'),
    ('Sri Lanka','LK'),
    ('Sudan','SD'),
    ('Suriname','SR'),
    ('Svalbard and Jan Mayen','SJ'),
    ('Swaziland','SZ'),
    ('U.S. Virgin Islands','VI'),
    ('Uganda','UG'),
    ('Ukraine','UA'),
    ('United Arab Emirates','AE'),
    ('United Kingdom','GB'),
    ('United States','US'),
    ('Uruguay','UY'),
    ('Uzbekistan','UZ'),
    ('Vanuatu','VU'),
    ('Vatican City','VA'),
    ('Venezuela','VE'),
    ('Vietnam','VN'),
    ('Wallis and Futuna','WF'),
    ('Western Sahara','EH'),
    ('Yemen','YE'),
    ('Zambia','ZM'),
    ('Zimbabwe','ZW');

UPDATE country
SET regex='^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$'
WHERE countryname='India';

UPDATE country
SET regex='(AT)?U[0-9]{8}'
WHERE countryname='Austria';



INSERT INTO validators VALUES
                           ('in', 'TaxIDProValidator'),
                           ('in', 'FonoaValidator');

SELECT code, regex FROM country where countryname='india';
SELECT taxvalidator FROM validators where countrycode='in';

UPDATE country SET regex='(SK)?[0-9]{10}' WHERE countryname='Slovakia';``

SELECT * FROM country;


UPDATE country SET regex='(AT)?U[0-9]{8}' WHERE countryname='Austria';
UPDATE country SET regex='(BE)?0[0-9]{9}' WHERE countryname='Belgium';
UPDATE country SET regex='(BG)?[0-9]{9,10}' WHERE countryname='Bulgaria';
UPDATE country SET regex='(HR)?[0-9]{11}' WHERE countryname='Croatia';
UPDATE country SET regex='(CY)?[0-9]{8}[A-Z]' WHERE countryname='Cyprus';
UPDATE country SET regex='(CZ)?[0-9]{8,10}' WHERE countryname='Czech';
UPDATE country SET regex='(DE)?[0-9]{9}' WHERE countryname='Germany';
UPDATE country SET regex='(DK)?[0-9]{8}' WHERE countryname='Denmark';
UPDATE country SET regex='(EE)?[0-9]{9}' WHERE countryname='Estonia';
UPDATE country SET regex='(EL)?[0-9]{9}' WHERE countryname='Greece';
UPDATE country SET regex='ES[A-Z][0-9]{7}(?:[0-9]|[A-Z])' WHERE countryname='Spain';
UPDATE country SET regex='(FI)?[0-9]{8}' WHERE countryname='Finland';
UPDATE country SET regex='(FR)?[0-9A-Z]{2}[0-9]{9}' WHERE countryname='France';
UPDATE country SET regex='(GB)?([0-9]{9}([0-9]{3})?|[A-Z]{2}[0-9]{3})' WHERE countryname='United';
UPDATE country SET regex='(HU)?[0-9]{8}' WHERE countryname='Hungary';
UPDATE country SET regex='(IE)?[0-9]{7}[A-Z]{1,2}' WHERE countryname='Ireland';
UPDATE country SET regex='(IE)?[0-9][A-Z][0-9]{5}[A-Z]' WHERE countryname='Ireland';
UPDATE country SET regex='(IT)?[0-9]{11}' WHERE countryname='Italy';
UPDATE country SET regex='(LT)?([0-9]{9}|[0-9]{12})' WHERE countryname='Lithuania';
UPDATE country SET regex='(LU)?[0-9]{8}' WHERE countryname='Luxembourg';
UPDATE country SET regex='(LV)?[0-9]{11}' WHERE countryname='Latvia';
UPDATE country SET regex='(MT)?[0-9]{8}' WHERE countryname='Malta';
UPDATE country SET regex='(NL)?[0-9]{9}B[0-9]{2}' WHERE countryname='Netherlands';
UPDATE country SET regex='(PL)?[0-9]{10}' WHERE countryname='Poland';
UPDATE country SET regex='(PT)?[0-9]{9}' WHERE countryname='Portugal';
UPDATE country SET regex='(RO)?[0-9]{2,10}' WHERE countryname='Romania';
UPDATE country SET regex='(SE)?[0-9]{12}' WHERE countryname='Sweden';
UPDATE country SET regex='(SI)?[0-9]{8}' WHERE countryname='Slovenia';
