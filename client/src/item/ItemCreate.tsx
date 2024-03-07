// import React, { ChangeEvent, FormEvent, useState } from "react";
// import AxiosInstance from "../common/AxiosInstance";

// interface FormData {
//   name: string;
//   price: number;
//   stock: number;
//   description: string;
//   category: string;
//   thumbnailImage: string | ArrayBuffer | null;
//   itemImages: (string | ArrayBuffer | null)[];
// }

// const ItemCreate: React.FC = () => {
//   const [formData, setFormData] = useState<FormData>({
//     name: "",
//     price: 0,
//     stock: 0,
//     description: "",
//     category: "",
//     thumbnailImage: null,
//     itemImages: [],
//   });

//   const handleChange = (
//     e: ChangeEvent<HTMLInputElement | HTMLSelectElement>
//   ) => {
//     const { name, value } = e.target;
//     setFormData({
//       ...formData,
//       [name]: value,
//     });
//   };

//   const handleThumbnailImageChange = (e: ChangeEvent<HTMLInputElement>) => {
//     const file = e.target.files && e.target.files[0];
//     if (file) {
//       const reader = new FileReader();
//       reader.onload = (event) => {
//         const encodedData = event.target?.result as string;
//         setFormData({
//           ...formData,
//           thumbnailImage: encodedData,
//         });
//       };
//       reader.readAsDataURL(file);
//     }
//   };

//   const handleItamImagesChange = (e: ChangeEvent<HTMLInputElement>) => {
//     const file = e.target.files && e.target.files[0];
//     console.log(file);
//     if (file) {
//       const reader = new FileReader();
//       reader.onload = (event) => {
//         const encodedData = event.target?.result;
//         if (encodedData) {
//           setFormData((prevFormData) => ({
//             ...prevFormData,
//             itemImages: [...prevFormData.itemImages, encodedData],
//           }));
//         }
//       };
//       reader.readAsDataURL(file);
//     }
//   };

//   const handleSubmit = async (e: FormEvent) => {
//     e.preventDefault();

//     const url = "http://localhost:8080/admin/item/add";

//     console.log("formData", formData);
//     try {
//       const response = await AxiosInstance.post(url, formData, {
//         headers: {
//           "Content-Type": "application/json",
//         },
//       });
//       console.log("Response:", response.data);
//       alert("아이템이 생성되었습니다!");
//     } catch (error) {
//       console.error("Error:", error);
//     }
//   };

//   return (
//     <div className="container">
//       <h3>관리자 아이템 등록 페이지</h3>
//       <form onSubmit={handleSubmit}>
//         <div className="form-group">
//           <label>Name:</label>
//           <input
//             type="text"
//             className="form-control"
//             name="name"
//             value={formData.name}
//             onChange={handleChange}
//           />
//         </div>
//         <div className="form-group">
//           <label>Description:</label>
//           <input
//             className="form-control"
//             type="text"
//             name="description"
//             value={formData.description}
//             onChange={handleChange}
//           />
//         </div>
//         <div className="form-group">
//           <label>Price:</label>
//           <input
//             className="form-control"
//             type="number"
//             name="price"
//             value={formData.price}
//             onChange={handleChange}
//           />
//         </div>
//         <div className="form-group">
//           <label>Stock:</label>
//           <input
//             className="form-control"
//             type="number"
//             name="stock"
//             value={formData.stock}
//             onChange={handleChange}
//           />
//         </div>
//         <div className="form-group">
//           <label>Category:</label>
//           <select
//             className="form-control"
//             name="category"
//             value={formData.category}
//             onChange={handleChange}
//           >
//             <option value="">카테고리 선택</option>
//             <option value="CLOTHING">의류</option>
//             <option value="ACCESSORIES">액세서리</option>
//             <option value="STATIONERY">문구류</option>
//             <option value="SPORTS_OUTDOOR">스포츠 및 야외용품</option>
//           </select>
//         </div>
//         <div className="form-group">
//           <label>Thumbnail Image:</label>
//           <input
//             className="form-control"
//             type="file"
//             name="thumbnailImage"
//             onChange={handleThumbnailImageChange}
//           />
//         </div>
//         <div className="form-group">
//           <label>Item Images:</label>
//           <input
//             className="form-control"
//             type="file"
//             name="itemImages"
//             multiple
//             onChange={handleItamImagesChange}
//           />
//         </div>
//         <button className="btn btn-primary" type="submit">
//           등록
//         </button>
//       </form>
//     </div>
//   );
// };

// export default ItemCreate;

import React, { ChangeEvent, FormEvent, useState } from "react";
import AxiosInstance from "../common/AxiosInstance";

interface FormData {
  name: string;
  price: number;
  stock: number;
  description: string;
  category: string;
  thumbnailImage: string | ArrayBuffer | null;
  itemImages: (string | ArrayBuffer | null)[];
}

const ItemCreate: React.FC = () => {
  const [formData, setFormData] = useState<FormData>({
    name: "",
    price: 0,
    stock: 0,
    description: "",
    category: "",
    thumbnailImage: null,
    itemImages: [],
  });

  const handleChange = (
    e: ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleThumbnailImageChange = (e: ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files && e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (event) => {
        const encodedData = event.target?.result as string;
        setFormData({
          ...formData,
          thumbnailImage: encodedData,
        });
      };
      reader.readAsDataURL(file);
    }
  };

  const handleItemImagesChange = (e: ChangeEvent<HTMLInputElement>) => {
    const files = e.target.files;
    console.log(files);
    if (files) {
      Array.from(files).forEach((file) => {
        const reader = new FileReader();
        reader.onload = (event) => {
          const encodedData = event.target?.result;
          if (encodedData) {
            setFormData((prevFormData) => ({
              ...prevFormData,
              itemImages: [...prevFormData.itemImages, encodedData],
            }));
          }
        };
        reader.readAsDataURL(file);
      });
    }
  };
  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();

    const url = "http://localhost:8080/admin/item/add";

    console.log("formData", formData);
    try {
      const response = await AxiosInstance.post(url, formData, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      console.log("Response:", response.data);
      alert("아이템이 생성되었습니다!");
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <div className="container">
      <h3>관리자 아이템 등록 페이지</h3>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Name:</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={formData.name}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>Description:</label>
          <input
            className="form-control"
            type="text"
            name="description"
            value={formData.description}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>Price:</label>
          <input
            className="form-control"
            type="number"
            name="price"
            value={formData.price}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>Stock:</label>
          <input
            className="form-control"
            type="number"
            name="stock"
            value={formData.stock}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>Category:</label>
          <select
            className="form-control"
            name="category"
            value={formData.category}
            onChange={handleChange}
          >
            <option value="">카테고리 선택</option>
            <option value="CLOTHING">의류</option>
            <option value="ACCESSORIES">액세서리</option>
            <option value="STATIONERY">문구류</option>
            <option value="SPORTS_OUTDOOR">스포츠 및 야외용품</option>
          </select>
        </div>
        <div className="form-group">
          <label>Thumbnail Image:</label>
          <input
            className="form-control"
            type="file"
            name="thumbnailImage"
            onChange={handleThumbnailImageChange}
          />
        </div>
        <div className="form-group">
          <label>Item Images:</label>
          <input
            className="form-control"
            type="file"
            name="itemImages"
            multiple
            onChange={handleItemImagesChange}
          />
        </div>
        <button className="btn btn-primary" type="submit">
          등록
        </button>
      </form>
    </div>
  );
};

export default ItemCreate;
