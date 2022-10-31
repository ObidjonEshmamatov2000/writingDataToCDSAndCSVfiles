using my.files as my from './db/data-model';

service CatalogService {
    @readonly entity FileInfo as projection on my.FileInfo;
    @readonly entity FileContent as projection on my.FileContent;
}