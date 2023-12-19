const APIURL = 'http://192.168.10.13:6400'

const itemDelete = async () => {
    const itemId = document.querySelector('#item_delete_id').value
    // delete patch action
    const response = await fetch(`${APIURL}/assets/items`, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            "id": itemId
        })
    });
    // get response
    const data = await response.json();
    document.querySelector('#item_response').textContent = JSON.stringify(data);

}

const itemSelect = async () => {
    // get action
    const response = await fetch(`${APIURL}/assets/items`, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    });
    // get response
    const data = await response.json();
    if(data.ref == "OK") {
        document.querySelector('#item_response').textContent = JSON.stringify(data);
        // Use Fetch API to send data to Java backend
        fetch(`send-data`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data.value)
        });
    }
    else document.querySelector('#item_response').textContent = JSON.stringify(data);
}

const itemInsert = async () => {
    const ServiceId = document.querySelector('#item_insert_service_id').value
    const ItemId = document.querySelector('#item_insert_id').value
    const ItemName = document.querySelector('#item_insert_name').value
    const ItemDescription = document.querySelector('#item_insert_description').value
    const ItemImg = document.querySelector('#item_insert_img').value
    const ItemNhid = document.querySelector('#item_insert_nhid').value
    const ItemIdx = document.querySelector('#item_insert_idx').value
    const TraitType = document.querySelector('#item_insert_attribute_name').value
    const TraitValue = document.querySelector('#item_insert_attribute_value').value

    // put patch action
    const response = await fetch(`${APIURL}/assets/items`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            "service_id": ServiceId,
            "item_id": ItemId,
            "item_name": ItemName,
            "item_description": ItemDescription,
            "item_img": ItemImg,
            "item_nhid": ItemNhid,
            "item_idx": ItemIdx,
            "item_attributes": [
                {
                    "trait_type": TraitType,
                    "value": TraitValue
                }
            ]
        })
    });

    // get response
    const data = await response.json();
    document.querySelector('#item_response').textContent = JSON.stringify(data);

}

// Trigger Click Event
const handleClick = async (event) => {
    const { target } = event;
    const dataset = target.dataset;
    switch (target.id) {
        // Item Insert
        case 'item_insert':
            itemInsert();
            break;
        // Item Select
        case 'item_select':
            itemSelect();
            break;
        // Item Delete
        case 'item_delete':
            itemDelete();
            break;
    }
}

document.addEventListener('click', handleClick);