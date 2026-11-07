package com.universidad.compras.aprobacion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionAprobacion {

    @Bean
    public ServicioAprobacion servicioAprobacion() {
        return new RevisorDeCumplimientoNormativo(
                new Supervisor(
                        new GerenteDeArea(
                                new DirectorFinanciero(null)
                        )
                )
        );
    }
}
