package com.jma.marketmotor.controller;

import com.jma.marketmotor.api.EmpleadoResponse;
import com.jma.marketmotor.dto.EmpleadoDto;
import com.jma.marketmotor.entity.EmpleadoEntity;
import com.jma.marketmotor.entity.UsuarioEntity;
import com.jma.marketmotor.mapping.EmpleadoMapper;
import com.jma.marketmotor.service.EmpleadoService;
import com.jma.marketmotor.service.UsuarioService;
import com.jma.marketmotor.utils.ConstantsService;
import com.jma.marketmotor.utils.EstadoD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService<EmpleadoEntity> empleadoService;
    private final UsuarioService<UsuarioEntity> usuarioService;

    @Autowired
    public EmpleadoController(EmpleadoService<EmpleadoEntity> empleadoService,UsuarioService<UsuarioEntity> usuarioService){
        this.empleadoService = empleadoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<List<EmpleadoEntity>> obtenerTodos(){
        return ResponseEntity.ok(empleadoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoEntity> obtenerPorId(@PathVariable("id") Long id){
        EmpleadoEntity empleado = empleadoService.obtenerPorId(id);
        if(empleado == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(empleado);
    }

    @PatchMapping("/desactivar/{id}")
    public ResponseEntity<String> desactivar(@PathVariable("id") Long id){

        try{
            EmpleadoEntity empleado = empleadoService.obtenerPorId(id);
            if(empleado == null)
                return ResponseEntity.notFound().build();

            empleado.declararDisponibilidad(EstadoD.INACTIVO);
            empleadoService.guardar(empleado);

            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id){
        try{
            empleadoService.eliminar(id);
            return ResponseEntity.ok("Se eliminó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<EmpleadoEntity> guardar(@RequestBody EmpleadoDto empleado){
        try{
            UsuarioEntity usuarioEntity = usuarioService.obtenerPorId(empleado.getIdUsuario());
            System.out.println(usuarioEntity);
            EmpleadoEntity empleadoToSave = EmpleadoMapper.mapToEntity(empleado);
            empleadoToSave.setUsuario(usuarioEntity);
            EmpleadoEntity empleadoGuardado = empleadoService.guardar(empleadoToSave);
            return ResponseEntity.ok(empleadoGuardado);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/guardarTodos")
    public ResponseEntity<List<EmpleadoEntity>> guardarTodos(@RequestBody List<EmpleadoDto> empleados){
        try{
            List<UsuarioEntity> usuariosObtenidosPorId = empleados.stream().map(e -> usuarioService.obtenerPorId(e.getIdUsuario())).toList();

            List<EmpleadoEntity> empleadosMapeados = empleados.stream().map(EmpleadoMapper::mapToEntity).toList();

            Iterator<UsuarioEntity> usuariosIterator = usuariosObtenidosPorId.iterator();
            Iterator<EmpleadoEntity> empleadosIterator = empleadosMapeados.iterator();

            List<EmpleadoEntity> empleadosToPersistence = new ArrayList<>();

           while (usuariosIterator.hasNext() && empleadosIterator.hasNext()) {
               EmpleadoEntity empleadoToList = empleadosIterator.next();
               empleadoToList.setUsuario(usuariosIterator.next());
               empleadosToPersistence.add(empleadoToList);
            }

           //Segunda manera de setear el usuario al empleado
           /* for (int i = 0; i < empleadosMapeados.size(); i++) {
                empleadosMapeados.get(i).setUsuario(usuariosObtenidosPorId.get(i));
                System.out.println("L");
                //String processedData = String.format("%s: %s", usuariosIterator.next().getId(), empleadosIterator.next().getNombre());
            } */

            List<EmpleadoEntity> empleadosGuardados = empleadoService.guardarTodos(empleadosToPersistence);
            return ResponseEntity.ok(empleadosGuardados);

        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public EmpleadoResponse obtenerTodosP(
            @RequestParam(value = "pageNo", defaultValue = ConstantsService.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ConstantsService.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ConstantsService.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ConstantsService.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return empleadoService.obtenerTodosP(pageNo, pageSize, sortBy, sortDir);
    }


}
