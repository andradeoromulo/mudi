package br.com.alura.mudi.api;

import br.com.alura.mudi.interceptor.AcessosInterceptor;
import br.com.alura.mudi.interceptor.AcessosInterceptor.Acesso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/acessos")
public class AcessosRest {

    @GetMapping
    public List<Acesso> getAcessos() {
        return AcessosInterceptor.acessos;
    }
}
