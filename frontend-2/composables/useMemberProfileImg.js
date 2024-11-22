export default function useGetImageUrl(imageFile = "", imageType = "member") {
  const config = useRuntimeConfig();

  //이미지 파일 이름이 없으면 기본 이미지 반환
  if (imageFile === null || imageFile === "" || imageFile === undefined) {
    return `/png/default-profile.png`;
  }

  //이미지가 경로면 경로 그대로 반환
  if (imageFile.substring(0, 4) === "http") {
    return imageFile;
  }
  //이미지가 Base64면 Base64 반환
  else if (imageFile.substring(0, 5) === "data:") {
    return imageFile;
  } else {
    //이미지가 이름이면 이미지 API 경로 반환
    return `${config.public.apiBase}/images/${imageType}/${imageFile}`;
  }
}
