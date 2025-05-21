import React, { useEffect, useState } from 'react';
import axios from 'axios';

const FormComponent = () => {
    const [mainLocationEnabled, setMainLocationEnabled] = useState(false);
    const [mainLocations, setMainLocations] = useState([]);
    const [form, setForm] = useState({
        product: '',
        billingAddress: '',
        shippingAddress: '',
        price: '',
        mainLocation: ''
    });

    useEffect(() => {
        checkFeatureFlag();
    }, []);

    const checkFeatureFlag = async () => {
        try {
            const res = await axios.get('http://localhost:8080/api/feature?featureName=show_main_locations');
            console.log('Feature flag status:', res.data);

            if (res.data === true) {
                setMainLocationEnabled(true);
                await getUserLocation();
            }
        } catch (err) {
            console.error('Feature check failed', err);
        }
    };

    const getUserLocation = async () => {
        try {
            let ip = sessionStorage.getItem('user_ip');

            if (!ip) {
                console.log("No IP in session storage, fetching new IP");
                const ipRes = await axios.get('https://api.ipify.org?format=json');
                ip = ipRes.data.ip;
                sessionStorage.setItem('user_ip', ip);
                console.log('Fetched new IP:', ip);
            } else {
                console.log('Using cached IP:', ip);
            }

            // Now fetch location with valid IP
            const locRes = await axios.get('http://localhost:8080/api/locations', {
                headers: { ip }
            });

            console.log('Location list:', locRes.data);
            setMainLocations(locRes.data);
        } catch (error) {
            console.error('Error fetching user location', error);
        }
    };

    const handleChange = (e) => {
        setForm(prev => ({ ...prev, [e.target.name]: e.target.value }));
    };

    return (
        <div style={{ maxWidth: 400, margin: 'auto' }}>
            <h2>Product Form</h2>
            <input type="text" name="product" placeholder="Product" onChange={handleChange} /><br /><br />
            <input type="text" name="quantity" placeholder="Quantity" onChange={handleChange} /><br /><br />
            <input type="text" name="billingAddress" placeholder="Billing Address" onChange={handleChange} /><br /><br />
            <input type="text" name="shippingAddress" placeholder="Shipping Address" onChange={handleChange} /><br /><br />
            <input type="text" name="price" placeholder="Price" onChange={handleChange} /><br /><br />

            {mainLocationEnabled && (
                <>
                    <select name="mainLocation" onChange={handleChange} value={form.mainLocation}>
                        <option value="">Select Main Location</option>
                        {mainLocations.map(loc => (
                            <option key={loc} value={loc}>{loc}</option>
                        ))}
                    </select>
                    <br /><br />
                </>
            )}

            <button onClick={() => console.log('Form submitted:', form)}>Submit</button>
        </div>
    );
};

export default FormComponent;
