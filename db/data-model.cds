namespace my.files;

entity FileInfo {
  key ID : String;
  name   : String;
  size   : Integer64;
  date   : String
}

entity FileContent {
  key ID  : String;
  content : String;
}