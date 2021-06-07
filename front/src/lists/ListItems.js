import React, { useContext, useEffect } from 'react';
import consumer from "../tools/consumer";
import events from "../tools/events";
import Store from "../store"

export default ({ listId, todo }) => {
    const { dispatch } = useContext(Store);
    const list = todo.elements.filter((element) => {
        return element.listId === listId;
    });
    useEffect(() => {
        consumer.findAll(listId).then((response) => {
            if(response.ok){
                response.json().then((items) => {
                    dispatch(events.finded(listId, items));
                })
            }
            
        });
    }, [listId, dispatch]);


    const onDelete = (itemId) => {
        consumer.delete(itemId).then((response) => {
            if(response.ok){
                dispatch(events.deleted(listId, itemId));
            }
        })
    };

    const onEdit = (item) => {
        dispatch(events.onEdited(listId, item))
    };

    const onChange = (event, item) => {
        const request = {
            name: item.name,
            id: item.id,
            completed: event.target.checked
        };
        consumer.update(listId, request).then((response) => {
            if(response.ok){
                response.json().then(() => {
                    dispatch(events.updated(listId, request))
                })
            }
        });
    };

    const decorationDone = {
        textDecoration: 'line-through',
        color: '#c3c3c3'
    };
    return <div>
        {todo.elements.length === 0 && <div><center><b>Dale click en agregar para almacenar un nuevo elemento en la lista.</b></center><br></br></div>}
        <table >
            <thead>
                <tr>
                    <td width="33%">ID</td>
                    <td width="33%">Tarea</td>
                    <td width="33%" >¿Completado?</td>
                    <td width="33%"></td>
                </tr>
            </thead>
            <tbody>
                {list.map((todo) => {
                    return <tr key={todo.id} style={todo.completed ? decorationDone : {}}  id={"to-do-"+todo.id}>
                        <td>{todo.id}</td>
                        <td>{todo.name}</td>
                        <td><input type="checkbox" defaultChecked={todo.completed} onChange={(event) => onChange(event, todo)}></input></td>
                        <td width="33%"><button className="button buttondelete" onClick={() => onDelete(todo.id)}>Eliminar</button></td>
                        <td width="33%"><button className="button buttonupdate" disabled={todo.completed} onClick={() => onEdit(todo)}>Editar</button></td>
                    </tr>
                })}
            </tbody>
        </table>
    </div>
}