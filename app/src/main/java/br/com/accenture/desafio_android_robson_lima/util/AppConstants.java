package br.com.accenture.desafio_android_robson_lima.util;

public interface AppConstants {
    String BASE_URL = "http://gateway.marvel.com/v1/public/";

    String KEY_DETAILS = "details";
    String KEY_COMICBOOK = "comicbooks";

    String NO_DESCRIPTION = "Sem descrição disponível.";

    String BAD_REQUEST = "Bad Request: Requisição inválida.";
    String UNAUTHORIZED = "Unauthorized: Acesso não autorizado.";
    String FORBIDDEN = "Forbidden: Acesso proibido.";
    String NOT_FOUND = "Not found: Servidor ou recurso não encontrado.";
    String CONFLICT = "Conflict: Conflito na requisição.";
    String BAD_GATEWAY = "Bad Gateway: Erro interno do gateway.";
    String GATEWAY_TIMEOUT = "Gateway Timeout: Timeout no gateway.";
    String UNKNOW_ERROR = "Erro não encontrado/desconhecido.";

    int OFFSET_VALUE = 20;

}
