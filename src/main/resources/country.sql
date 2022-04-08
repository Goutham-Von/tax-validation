CREATE TABLE taxvalidators (
                               id INTEGER PRIMARY KEY,
                               taxvalidator varchar(50),
                               isworking char(3) DEFAULT 'YES'
);

CREATE TABLE country (
                         countryname varchar(200) PRIMARY KEY,
                         code char(2) DEFAULT NULL,
                         validatorid INTEGER DEFAULT NULL,
                         FOREIGN KEY (validatorid) REFERENCES taxvalidators(id)
);

INSERT INTO taxvalidators(id, taxvalidator) VALUES
                                                (0, 'TaxIDProValidator'),
                                                (1, 'FonoaValidator');


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

INSERT INTO country(countryname, code, validatorid) VALUES
    ('India','IN', 0);

-- SELECT country.code, taxvalidators.taxvalidator
-- FROM country
--          INNER JOIN taxvalidators ON taxvalidators.id = country.validatorid
-- where country.countryname='india';