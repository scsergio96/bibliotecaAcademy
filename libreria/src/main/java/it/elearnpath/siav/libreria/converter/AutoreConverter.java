package it.elearnpath.siav.libreria.converter;

import it.elearnpath.siav.libreria.dto.AutoreDTO;
import it.elearnpath.siav.libreria.entity.Autore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutoreConverter {

    public static AutoreDTO convert(Autore autore) {

        if (autore == null) {
            return null;
        }

        AutoreDTO autoreDTO = new AutoreDTO();

        autoreDTO.setId(autore.getId());
        autoreDTO.setNome(autore.getNome());
        autoreDTO.setCognome(autore.getCognome());
        autoreDTO.setBiografia(autore.getBiografia());
        autoreDTO.setDataNascita(converterDateToString(autore.getDataNascita()));
        autoreDTO.setDataMorte(converterDateToString(autore.getDataMorte()));
        autoreDTO.setNazionalita(autore.getNazionalita());

        return autoreDTO;
    }

    public static Autore convert(AutoreDTO autoreDTO) {

        if (autoreDTO == null) {
            return null;
        }

        Autore autore = new Autore();

        autore.setId(autoreDTO.getId());
        autore.setNome(autoreDTO.getNome());
        autore.setCognome(autoreDTO.getCognome());
        autore.setBiografia(autoreDTO.getBiografia());
        autore.setDataNascita(converterStringToDate(autoreDTO.getDataNascita()));
        autore.setDataMorte(converterStringToDate(autoreDTO.getDataMorte()));
        autore.setNazionalita(autoreDTO.getNazionalita());

        return autore;
    }

    private static String converterDateToString(Date date) {
        if(date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.format(date).toString();
        } else return null;
    }

    private static Date converterStringToDate(String date) {
        if(date != "" && date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return simpleDateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }

}
