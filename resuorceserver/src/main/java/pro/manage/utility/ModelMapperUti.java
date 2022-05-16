package pro.manage.utility;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public  class ModelMapperUti {

    final static ModelMapper modelMapper=new ModelMapper();
    public static  <T,TT> List<TT> mapList(List<T> list, Class<TT> ttClass){
        return     list.stream().map(c->modelMapper.map(c,ttClass)).collect(Collectors.toList());
    }
    public static <T,TT> TT map(T t,Class<TT> ttClass){
        return  modelMapper.map(t,ttClass);
    }
}