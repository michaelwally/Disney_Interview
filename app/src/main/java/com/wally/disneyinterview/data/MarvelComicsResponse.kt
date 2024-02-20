package com.wally.disneyinterview.data

data class MarvelComicsResponse<T>(
    val data: Data<T>
)
data class Data<T>(
    val results: List<T>
)

data class ComicData(
    val title: String,
    val textObjects: List<TextObject>,
    val thumbnail: Thumbnail
)

data class TextObject(
    val text: String
)

data class Thumbnail(
    val path: String,
    val extension: String
)

//{
//  "code": 200,
//  "status": "Ok",
//  "copyright": "© 2024 MARVEL",
//  "attributionText": "Data provided by Marvel. © 2024 MARVEL",
//  "attributionHTML": "<a href=\"http://marvel.com\">Data provided by Marvel. © 2024 MARVEL</a>",
//  "etag": "cd3a3759fcf6a33bea49e6632e22639039ee2a02",
//  "data": {
//    "results": [
//    {
//        "id": 291,
//        "digitalId": 0,
//        "title": "Ant-Man (2003) #1",
//        "description": "",
//        "textObjects": [
//        {
//            "type": "issue_solicit_text",
//            "language": "en-us",
//            "text": "Size does matter.  And no one knows this more than Hank Pym - a.k.a. Ant-Man. Got a problem with Galactus? Call the FF. Got a problem with, say, mind-controlled cockroaches? Then Ant-Man's your man! And needless to say, it's done a number on our diminutive hero's self-esteem.  When Ant-Man is tapped to infiltrate an international spy ring that has been siphoning secrets out of Washington, he jumps at the chance - unaware that he's being used as a pawn in a larger game of espionage.\r\n32 PGS./PARENTAL ADVISORY...$2.99"
//        }
//        ],
//        "thumbnail": {
//        "path": "http://i.annihil.us/u/prod/marvel/i/mg/6/e0/4bc6a2497684e",
//        "extension": "jpg"
//    },