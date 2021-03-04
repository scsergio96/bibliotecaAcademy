package it.elearnpath.siav.registry.converter;

import it.elearnpath.siav.registry.dto.LibraryCardDTO;
import it.elearnpath.siav.registry.entity.LibraryCard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryCardConverter {

    public static LibraryCardDTO convert(LibraryCard libraryCard) {

        if (libraryCard == null) {
            return null;
        }

        LibraryCardDTO libraryCardDTO = new LibraryCardDTO();
        libraryCardDTO.setId(libraryCard.getId());
        libraryCardDTO.setExpiration(converterDateToString(libraryCard.getExpiration()));
        libraryCardDTO.setValid(libraryCard.isValid());

        return libraryCardDTO;
    }

    public static LibraryCard convert(LibraryCardDTO libraryCardDTO) {

        if (libraryCardDTO == null) {
            return null;
        }

        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setId(libraryCardDTO.getId());
        libraryCard.setExpiration(converterStringToDate(libraryCardDTO.getExpiration()));
        libraryCard.setValid(libraryCardDTO.isValid());

        return libraryCard;
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
