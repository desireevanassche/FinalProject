const  { S3Client } = require('@aws-sdk/client-s3');
const { Module } = require('module');
const multer = require('multer');
const multerS3 = require('multer-s3');

AWS.config.update({
  secretAccessKey: "PFjRocdXISYlEWMaBzvIwPYOpVqNnuPAyh3AAOdb",
  accessKeyId: "AKIAQSNDTSCNFXOG4IC4",
  region: "us-west-2"
});



const s3 = new S3Client();

const upload = multer({
  storage: multerS3({
    s3: s3,
    bucket: 'plantdaddy',
    metadata: function (req, file, cb) {
      cb(null, {fieldName: "TESTING_META_DATA"});
    },
    key: function (req, file, cb) {
      cb(null, Date.now().toString())
    }
  })
})


Module.exports = upload;
